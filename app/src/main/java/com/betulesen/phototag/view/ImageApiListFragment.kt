package com.betulesen.phototag.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.betulesen.phototag.R
import com.betulesen.phototag.adapter.ImageApiListAdapter
import com.betulesen.phototag.databinding.FragmentImageApiListBinding
import com.betulesen.phototag.model.ResultPhotoTag
import com.betulesen.phototag.util.resource.Resource
import com.betulesen.phototag.viewmodel.PhotoTagViewmodel
import com.bumptech.glide.RequestManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageApiListFragment @Inject constructor(
    private val imageApiListAdapter: ImageApiListAdapter
): Fragment() {

    private var _binding: FragmentImageApiListBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel : PhotoTagViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageApiListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(PhotoTagViewmodel::class.java)

        subscribeToObservers()

        var job : Job? = null
        binding.searchEditText.addTextChangedListener{
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let{
                    if(it.toString().isNotEmpty()){
                        viewModel.searchPhoto(it.toString())
                    }
                }
            }
        }

        binding.imageApiListRecylerView.adapter = imageApiListAdapter
        binding.imageApiListRecylerView.layoutManager= GridLayoutManager(requireContext(),3)
        imageApiListAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setSelectedPhoto(it)
        }

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
    }

    fun subscribeToObservers(){
        viewModel.photoList.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Resource.Status.SUCCESS -> {
                    var urls = it.data?.hits?.map { ResultPhotoTag ->
                        ResultPhotoTag.previewURL
                    }
                    imageApiListAdapter.imagesApiList = urls ?: listOf()
                    _binding?.progressBar?.visibility = View.GONE
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(requireContext(),it.message ?: "Error",Toast.LENGTH_LONG).show()
                    _binding?.progressBar?.visibility = View.GONE
                }
                Resource.Status.LOADING -> {
                    _binding?.progressBar?.visibility = View.VISIBLE
                }

            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}