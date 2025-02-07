package com.betulesen.phototag.api

import com.betulesen.phototag.model.ResponsePhotoTag
import com.betulesen.phototag.util.constants.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServises {

    @GET("/api/")
    suspend fun imageSearch(
        @Query("q") searchQuery : String,
        @Query("key") apiKey :String = API_KEY

    ) : Response<ResponsePhotoTag>
}