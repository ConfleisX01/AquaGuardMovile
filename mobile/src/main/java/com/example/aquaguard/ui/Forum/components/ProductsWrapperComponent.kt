package com.example.aquaguard.ui.Forum.components

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aquaguard.data.models.ProductModel
import com.example.aquaguard.ui.components.SkeletonCard

@Composable
fun ProductsWrapper(products: List<ProductModel>, isProductsData: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            "Productos",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal)
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            if (isProductsData) {
                products.forEach { item ->
                    ProductItem(
                        title = item.name,
                        description = item.description,
                        imageUrl = "https://7xrpfkt5-5002.usw3.devtunnels.ms/uploads/${item.image}",
                        price = item.price,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                            .padding(bottom = 12.dp)
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    SkeletonCard(isImageActive = true)
                }
            }
        }
    }
}