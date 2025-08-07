package com.example.aquaguard.ui.Forum.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.config.RetrofitClientNewsData
import com.example.aquaguard.data.config.RetrofitClientProductData
import com.example.aquaguard.data.models.NewsModel
import com.example.aquaguard.data.models.ProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForumViewModel : ViewModel() {
    private val _productsList = MutableStateFlow<List<ProductModel>>(emptyList())
    val productsList: StateFlow<List<ProductModel>> = _productsList

    private val _isProductData = MutableStateFlow(false)
    val isProductData: StateFlow<Boolean> = _isProductData

    private val _newsList = MutableStateFlow<List<NewsModel>>(emptyList())
    val newsList: StateFlow<List<NewsModel>> = _newsList

    private val _isNewsData = MutableStateFlow(false)
    val isNewsData: StateFlow<Boolean> = _isNewsData

    fun getProductsList() {
        viewModelScope.launch {
            try {
                val response = RetrofitClientProductData.retrofit.getData()
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        _productsList.value = data
                        _isProductData.value = true
                    }
                } else {
                    Log.e("PRODUCTS", "Error obtener los productos")
                    _isProductData.value = false
                }
            } catch (e: Exception) {
                Log.e("PRODUCTS", "Error de servidor")
            }
        }
    }

    fun getNewsList() {
        viewModelScope.launch {
            try {
                val response = RetrofitClientNewsData.retrofit.getNews()
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        _newsList.value = data
                        _isNewsData.value = true
                    }
                } else {
                    Log.e("NEWS", "Error obtener las noticias")
                    _isNewsData.value = false
                }
            } catch (e: Exception) {
                Log.e("NEWS", "Error de servidor")
            }
        }
    }
}