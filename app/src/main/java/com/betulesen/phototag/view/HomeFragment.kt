package com.betulesen.phototag.view



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.betulesen.phototag.R
import com.betulesen.phototag.adapter.PhotoTagAdapter
import com.betulesen.phototag.databinding.FragmentHomeBinding
import com.betulesen.phototag.viewmodel.PhotoTagViewmodel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity



class HomeFragment @Inject constructor(
    val photoTagAdapter : PhotoTagAdapter
) : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel : PhotoTagViewmodel

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val selectedPhotoTag = photoTagAdapter.photoTagList[layoutPosition]
            viewModel.deletePhoto(selectedPhotoTag)

            Snackbar.make(viewHolder.itemView, "Photo deleted", Snackbar.LENGTH_LONG).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(PhotoTagViewmodel::class.java)

        subscribeToObservers()
        binding.homeListRecyclerView.adapter = photoTagAdapter
        binding.homeListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.homeListRecyclerView)

        binding.fab.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
            findNavController().navigate(action)
        }

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

    }


    private fun subscribeToObservers(){
        viewModel.photoTagList.observe(viewLifecycleOwner, Observer {
            photoTagAdapter.photoTagList = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}