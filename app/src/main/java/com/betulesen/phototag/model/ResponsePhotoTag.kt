package com.betulesen.phototag.model

data class ResponsePhotoTag(
    val hits : List<ResultPhotoTag>,
    val total : Int,
    val totalHits : Int
)