package com.example.aquaguard.ui.Forum.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Alignment

@Composable
fun ProductItem(
    title: String,
    description: String,
    imageUrl: String,
    price: Float,
    modifier: Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = modifier
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Imagen del producto",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f)
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "$${price}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}