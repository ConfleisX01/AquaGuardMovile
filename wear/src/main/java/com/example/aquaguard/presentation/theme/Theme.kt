// com.example.aquaguard.presentation.theme.Theme.kt
package com.example.aquaguard.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.Typography

@Composable
fun AquaGuardWearTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        Colors(
            primary = SkyBlue,
            primaryVariant = LightBlue,
            secondary = AquaBlue,
            secondaryVariant = AquaBlue,
            background = DeepBlue,
            surface = DarkSurface,
            onPrimary = White,
            onSecondary = White,
            onBackground = White,
            onSurface = White,
            error = Color.Red,
            onError = Color.White,
        )
    } else {
        Colors(
            primary = SkyBlue,
            primaryVariant = LightBlue,
            secondary = AquaBlue,
            secondaryVariant = AquaBlue,
            background = SoftWhite,
            surface = White,
            onPrimary = DeepBlue,
            onSecondary = DeepBlue,
            onBackground = DeepBlue,
            onSurface = DeepBlue,
            error = Color.Red,
            onError = Color.White,
        )
    }

    MaterialTheme(
        colors = colors,
        typography = Typography(), // Puedes personalizarlo si quieres
        content = content
    )
}
