package com.example.imageloadpractical

import com.example.imageloadpractical.model.ImageModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("photos")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<ImageModel>
}