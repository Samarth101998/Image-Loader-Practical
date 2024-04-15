package com.example.imageloadpractical

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imageloadpractical.Repository.ImageRepository
import com.example.imageloadpractical.model.ImageModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ImagesPagingSource @Inject constructor(
    private val repository: ImageRepository
) : PagingSource<Int, ImageModel>() {
    override fun getRefreshKey(state: PagingState<Int, ImageModel>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageModel> {
        val page = params.key ?: 1
        val response = repository.getImages(page, params.loadSize)
        return try {
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        } catch (e: IOException) {
            Log.e("Error", "load: ", e)
            LoadResult.Error(
                e
            )
        } catch (e: HttpException) {
            Log.e("Error", "load: ", e)
            LoadResult.Error(
                e
            )
        }
    }
}