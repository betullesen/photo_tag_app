package com.betulesen.phototag.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.betulesen.phototag.R
import com.betulesen.phototag.databinding.FragmentDetailBinding
import com.betulesen.phototag.util.resource.Resource
import com.betulesen.phototag.viewmodel.PhotoTagViewmodel
import com.bumptech.glide.RequestManager
import javax.inject.Inject


class DetailFragment @Inject constructor(
    val glide : RequestManager
) : Fragment() {

    lateinit var viewModel : PhotoTagViewmodel

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(PhotoTagViewmodel::class.java)


        binding.detailImageView.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToImageApiListFragment()
            findNavController().navigate(action)
        }

        subscribeToObservers()

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.saveDetailButton.setOnClickListener {
            viewModel.makePhotoTag(binding.imageNameDetail.text.toString(),
                binding.description.text.toString(),
                binding.dateDetail.text.toString())
        }

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

    }

    private fun subscribeToObservers(){
        viewModel.selectedPhotoUrl.observe(viewLifecycleOwner, Observer { url ->
            _binding?.let {
                glide.load(url).into(it.detailImageView)
            }
        })

        viewModel.insertPhotoMessage.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Resource.Status.SUCCESS -> {
                    Toast.makeText(requireContext(),"Success",Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                    viewModel.resetInsertPhotoMsg()
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(requireContext(),it.message?: "Error",Toast.LENGTH_LONG).show()

                }
                Resource.Status.LOADING -> {

                }

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}