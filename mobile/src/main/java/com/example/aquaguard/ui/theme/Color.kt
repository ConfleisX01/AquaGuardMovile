package com.example.aquaguard.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Paleta inspirada en agua pluvial y lluvia ---

val RainPrimary = Color(0xFF3D5A80)           // Azul oscuro suave (agua profunda)
val RainPrimaryContainer = Color(0xFF98C1D9)  // Azul claro opaco (agua limpia)
val RainSecondary = Color(0xFF5C7080)         // Gris azulado (nube húmeda)
val RainBackground = Color(0xFFE0E5EC)        // Gris claro casi blanco (nublado)
val RainSurface = Color(0xFFF7FAFC)           // Blanco azulado (niebla)
val RainError = Color(0xFFEF5350)             // Rojo suave

val RainOnPrimary = Color.White
val RainOnSecondary = Color(0xFFFAFAFA)
val RainOnBackground = Color(0xFF1C1C1C)
val RainOnSurface = Color(0xFF1C1C1C)


// --- Tema claro ---

private val AquaCycleLightColorScheme = lightColorScheme(
    primary = RainPrimary,
    onPrimary = RainOnPrimary,
    primaryContainer = RainPrimaryContainer,
    onPrimaryContainer = RainOnPrimary,
    secondary = RainSecondary,
    onSecondary = RainOnSecondary,
    background = RainBackground,
    onBackground = RainOnBackground,
    surface = RainSurface,
    onSurface = RainOnSurface,
    error = RainError,
    onError = Color.White
)

// --- Tema oscuro ---

private val AquaCycleDarkColorScheme = darkColorScheme(
    primary = Color(0xFF293241),           // Azul muy oscuro (lluvia nocturna)
    onPrimary = Color.White,
    primaryContainer = RainPrimary,
    onPrimaryContainer = Color.White,
    secondary = Color(0xFF4B5B6E),
    onSecondary = Color.White,
    background = Color(0xFF121212),
    onBackground = Color(0xFFE0E0E0),
    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFF0F0F0),
    error = RainError,
    onError = Color.White
)


// --- Tipografía ---

val AquaCycleTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)


// --- Formas ---

val AquaCycleShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(12.dp)
)


// --- Tema general ---

@Composable
fun AquaCycleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        AquaCycleDarkColorScheme
    } else {
        AquaCycleLightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = AquaCycleTypography,
        shapes = AquaCycleShapes,
        content = content
    )
}