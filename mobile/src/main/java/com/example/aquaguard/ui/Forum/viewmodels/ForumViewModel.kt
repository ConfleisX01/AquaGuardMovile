package com.example.aquaguard.ui.Forum.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquaguard.data.config.RetrofitClientProductData
import com.example.aquaguard.data.models.ProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForumViewModel : ViewModel() {
    private val _productsList = MutableStateFlow<List<ProductModel>>(emptyList())
    val productsList: StateFlow<List<ProductModel>> = _productsList

    fun getProductsList() {
        viewModelScope.launch {
            try {
                val response = RetrofitClientProductData.retrofit.getData()
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        _productsList.value = data
                    }
                } else {
                    Log.e("PRODUCTS", "Error obtener los productos")
                }
            } catch (e: Exception) {
                Log.e("PRODUCTS", "Error de servidor")
            }
        }
    }
}