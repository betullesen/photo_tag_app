package com.betulesen.phototag.repository

import androidx.lifecycle.LiveData
import com.betulesen.phototag.api.ApiServises
import com.betulesen.phototag.model.ResponsePhotoTag
import com.betulesen.phototag.roomDb.PhotoTag
import com.betulesen.phototag.roomDb.PhotoTagDao
import com.betulesen.phototag.util.resource.Resource
import javax.inject.Inject

class PhotoTagRepository @Inject constructor(
    private val photoTagDao: PhotoTagDao,
    private val apiServises : ApiServises
){

    suspend fun insertPhotoTag(photoTag : PhotoTag) {
        photoTagDao.insertPhotoTag(photoTag)
    }

    suspend fun deletePhotoTag(photoTag: PhotoTag) {
        photoTagDao.deletePhotoTag(photoTag)
    }

    fun observePhotoTags(): LiveData<List<PhotoTag>> {
        return photoTagDao.observePhotoTag()
    }


    suspend fun searchPhotos(query: String): Resource<ResponsePhotoTag> {
        return try {
            val response = apiServises.imageSearch(query)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Empty Response")
            } else {
                Resource.error("API Error: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.error("Network Error: ${e.message}")
        }
    }

}