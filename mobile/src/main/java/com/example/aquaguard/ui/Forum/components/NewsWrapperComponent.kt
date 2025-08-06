package com.example.aquaguard.ui.Forum.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun NewsWrapper() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text("Noticias", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal))
        NewsItem(
            title = "¡Bienvenido a AquaGuard!",
            content = "Gracias por unirte a nuestra comunidad. Aquí podrás compartir ideas, resolver dudas y aprender más sobre el cuidado y uso inteligente del agua. ¡Explora el foro y participa!",
            isImportant = true
        )
        NewsItem(
            title = "¡Nuevos productos disponibles!",
            content = "Explora nuestros nuevos sistemas de filtrado para el hogar y sensores inteligentes compatibles con AquaGuard.",
            isImportant = true
        )
    }
}