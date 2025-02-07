package com.betulesen.phototag.roomDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photoTags")
data class PhotoTag (
    var imageName : String,
    var artistName : String,
    var date : Int,
    var imageUrl : String,
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null
)