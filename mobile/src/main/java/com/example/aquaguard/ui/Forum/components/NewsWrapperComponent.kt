package com.example.aquaguard.ui.Forum.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.aquaguard.data.models.NewsModel
import com.example.aquaguard.ui.components.SkeletonCard

@Composable
fun NewsWrapper(newsList: List<NewsModel>, isNewsData: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text("Noticias", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal))
        if (isNewsData) {
            newsList.forEach { data ->
                NewsItem(
                    title = data.title,
                    content = data.description,
                    isImportant = data.isImportant
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                SkeletonCard()
            }
        }
    }
}