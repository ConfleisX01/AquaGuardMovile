package com.example.aquaguard.ui.Forum

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aquaguard.ui.Forum.components.NewsWrapper
import com.example.aquaguard.ui.Forum.components.ProductsWrapper
import com.example.aquaguard.ui.Forum.viewmodels.ForumViewModel


@Composable
@Preview(showBackground = true)
fun ForumScreen() {
    val productsViewModel: ForumViewModel = viewModel()
    val productsList by productsViewModel.productsList.collectAsState()

    LaunchedEffect(Unit) {
        productsViewModel.getProductsList()
    }
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            "Foro AquaGuard",
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
        NewsWrapper()
        ProductsWrapper(productsList)
    }
}
