package com.example.aquaguard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
fun SkeletonCard(
    modifier: Modifier = Modifier,
    isLoading: Boolean = true,
    isImageActive: Boolean = false
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        tonalElevation = 2.dp,
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            if (isImageActive) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .placeholder(visible = isLoading, highlight = PlaceholderHighlight.shimmer(), color = MaterialTheme.colorScheme.secondary)
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(20.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .placeholder(visible = isLoading, highlight = PlaceholderHighlight.shimmer(), color = MaterialTheme.colorScheme.secondary)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(14.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .placeholder(visible = isLoading, highlight = PlaceholderHighlight.shimmer(), color = MaterialTheme.colorScheme.secondary)
            )
        }
    }
}