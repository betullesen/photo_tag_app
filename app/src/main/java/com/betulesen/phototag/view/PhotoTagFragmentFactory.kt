package com.betulesen.phototag.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.betulesen.phototag.adapter.ImageApiListAdapter
import com.betulesen.phototag.adapter.PhotoTagAdapter
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class PhotoTagFragmentFactory @Inject constructor(
    private val photoTagAdapter : PhotoTagAdapter,
    private val imageApiListAdapter : ImageApiListAdapter,
    private val glide : RequestManager
) : FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){
            HomeFragment::class.java.name -> HomeFragment(photoTagAdapter)
            ImageApiListFragment::class.java.name -> ImageApiListFragment(imageApiListAdapter)
            DetailFragment::class.java.name -> DetailFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}