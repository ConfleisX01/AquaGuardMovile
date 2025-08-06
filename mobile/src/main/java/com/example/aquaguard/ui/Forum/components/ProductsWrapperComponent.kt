package com.example.aquaguard.ui.Forum.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aquaguard.data.models.ProductModel

@Composable
fun ProductsWrapper(products: List<ProductModel>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text("Productos", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal))
        if (!products.isEmpty()) {
            products.forEach { item ->
                ProductItem(
                    title = item.name,
                    description = item.description,
                    imageUrl = item.imageFile,
                    price = item.price
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Aun no hay productos para mostrar", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal))
            }
        }
    }
}