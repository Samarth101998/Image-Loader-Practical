package com.example.imageloadpractical.viewModel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.imageloadpractical.ApiService
import com.example.imageloadpractical.ImagesPagingSource
import com.example.imageloadpractical.helper.Const
import com.example.imageloadpractical.model.ImageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imagesPagingSource: ImagesPagingSource,
    application: Application
) : AndroidViewModel(application) {
    private val _imageResponse: MutableStateFlow<PagingData<ImageModel>> =
        MutableStateFlow(PagingData.empty())
    var imageResponse = _imageResponse.asStateFlow()
        private set

    init {
        viewModelScope.launch {
            Pager(
               config = PagingConfig(pageSize = 20, enablePlaceholders = true)
            ){
                imagesPagingSource
            }.flow.cachedIn(viewModelScope).collect{
                _imageResponse.value = it
            }
        }
    }
}