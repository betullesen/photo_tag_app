package com.betulesen.phototag.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.betulesen.phototag.R
import com.bumptech.glide.RequestManager
import com.google.android.material.animation.AnimatableView.Listener
import javax.inject.Inject

class ImageApiListAdapter @Inject constructor(
    val glide : RequestManager
): RecyclerView.Adapter<ImageApiListAdapter.ImageApiListViewHolder>() {

    private var onItemClickListener : ((String) -> Unit)? = null

    class ImageApiListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    private val imageApiListDiffer = AsyncListDiffer(this,diffUtil)

    var imagesApiList : List<String>
        get() = imageApiListDiffer.currentList
        set(value) = imageApiListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageApiListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.api_list_row,parent,false)
        return ImageApiListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imagesApiList.size
    }

    fun setOnItemClickListener(listener: (String) -> Unit){
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: ImageApiListViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.apiListRowImageView)
        val url = imagesApiList[position]
        holder.itemView.apply {
            glide.load(url).into(imageView)
            setOnClickListener{
                onItemClickListener?.let {
                    it(url)
                }
            }
        }
    }
}