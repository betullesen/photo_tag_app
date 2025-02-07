package com.betulesen.phototag.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betulesen.phototag.model.ResponsePhotoTag
import com.betulesen.phototag.repository.PhotoTagRepository
import com.betulesen.phototag.roomDb.PhotoTag
import com.betulesen.phototag.util.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoTagViewmodel @Inject constructor(
    private val repository : PhotoTagRepository
) : ViewModel() {

    val photoTagList = repository.observePhotoTags()

    private val photos = MutableLiveData<Resource<ResponsePhotoTag>>()
    val photoList : LiveData<Resource<ResponsePhotoTag>>
        get() = photos


    private val selectedPhoto = MutableLiveData<String>()
    val selectedPhotoUrl : LiveData<String>
        get() = selectedPhoto


    private var insertPhotoMsg = MutableLiveData<Resource<PhotoTag>>()
    val insertPhotoMessage : LiveData<Resource<PhotoTag>>
        get() = insertPhotoMsg


    fun resetInsertPhotoMsg(){
        insertPhotoMsg = MutableLiveData<Resource<PhotoTag>>()
    }

    fun setSelectedPhoto(url : String){
        selectedPhoto.postValue(url)
    }

    fun deletePhoto(photoTag: PhotoTag) = viewModelScope.launch{
        repository.deletePhotoTag(photoTag)
    }

    fun insertPhoto(photoTag: PhotoTag) = viewModelScope.launch {
        repository.insertPhotoTag(photoTag)
    }

    fun makePhotoTag(imageName : String,artistName : String, date : String){
        if(imageName.isEmpty() || artistName.isEmpty() || date.isEmpty()){
            insertPhotoMsg.postValue(Resource.error("Please enter a value"))
            return
        }
         val dateInt = try {
             date.toInt()
         }catch (e:Exception){
             insertPhotoMsg.postValue(Resource.error("Date should be number"))
             return
         }
        val photoTag = PhotoTag(imageName,artistName,dateInt,selectedPhoto.value?: "")
        insertPhoto(photoTag)
        setSelectedPhoto("")
        insertPhotoMsg.postValue(Resource.success(photoTag))
    }

    fun searchPhoto(searchString : String){
        if(searchString.isEmpty()){
            return
        }
        photos.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.searchPhotos(searchString)
            photos.value = response
        }


    }

}

