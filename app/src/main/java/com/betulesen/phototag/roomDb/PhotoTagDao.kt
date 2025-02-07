package com.betulesen.phototag.roomDb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhotoTagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotoTag(photoTag: PhotoTag)

    @Delete
    suspend fun deletePhotoTag(photoTag: PhotoTag)

    @Query("SELECT* FROM photoTags")
    fun observePhotoTag() : LiveData<List<PhotoTag>>
}