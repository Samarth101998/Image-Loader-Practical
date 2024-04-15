package com.example.imageloadpractical.Repository

import com.example.imageloadpractical.ApiService
import com.example.imageloadpractical.model.ImageModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class ImageRepository @Inject constructor(
    private val apiService: ApiService
){
    suspend fun getImages(
        page: Int,
        limit: Int
    ): List<ImageModel> = apiService.getImages(
        page, limit
    )
}