package com.betulesen.phototag.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.betulesen.phototag.R
import com.betulesen.phototag.roomDb.PhotoTag
import com.bumptech.glide.RequestManager
import org.w3c.dom.Text
import javax.inject.Inject

class PhotoTagAdapter @Inject constructor(
    val glide : RequestManager
) : RecyclerView.Adapter<PhotoTagAdapter.PhotoTagViewHolder>(){

    class  PhotoTagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    
    private val diffUtil = object : DiffUtil.ItemCallback<PhotoTag>() {
        override fun areItemsTheSame(oldItem: PhotoTag, newItem: PhotoTag): Boolean {
            return oldItem== newItem
        }

        override fun areContentsTheSame(oldItem: PhotoTag, newItem: PhotoTag): Boolean {
            return oldItem== newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)
    var photoTagList : List<PhotoTag>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoTagViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_row,parent,false)
        return PhotoTagViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photoTagList.size
    }

    override fun onBindViewHolder(holder: PhotoTagViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.imageViewListRow)
        val nameText = holder.itemView.findViewById<TextView>(R.id.imageNameListRow)
        val artistNameText = holder.itemView.findViewById<TextView>(R.id.descriptionListRow)
        val dateText = holder.itemView.findViewById<TextView>(R.id.dateListRow)
        val photoTags = photoTagList[position]
        holder.itemView.apply {
            nameText.text = photoTags.imageName
            artistNameText.text=photoTags.artistName
            dateText.text = "Year: ${photoTags.date}"
            glide.load(photoTags.imageUrl).into(imageView)
        }

    }
}