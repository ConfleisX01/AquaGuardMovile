// src/main/java/com/yourpackagename/ui/theme/Theme.kt
// Reemplaza 'com.yourpackagename' con el nombre real de tu paquete.

package com.example.aquaguard.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.material3.Shapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable

// --- Colores ---
val PrimaryBlue = Color(0xFF42A5F5)        // Azul Cielo Claro
val PrimaryBlueVariant = Color(0xFF1976D2)  // Azul Medio
val SecondaryLightBlue = Color(0xFF81D4FA) // Azul Hielo
val BackgroundLight = Color(0xFFF5F5F5)    // Gris Muy Claro
val SurfaceWhite = Color(0xFFFFFFFF)       // Blanco Puro
val ErrorRed = Color(0xFFEF5350)           // Rojo Suave

val TextDark = Color(0xFF212121)            // Gris Oscuro para texto
val TextLight = Color(0xFFFFFFFF)          // Blanco para texto sobre colores oscuros
val TextOnSecondary = Color(0xFF000000)    // Negro para texto sobre SecondaryLightBlue

// Esquema de colores para el tema claro
private val AquaCycleLightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = TextLight,
    primaryContainer = PrimaryBlueVariant, // Usamos PrimaryBlueVariant para primaryContainer
    onPrimaryContainer = TextLight,
    secondary = SecondaryLightBlue,
    onSecondary = TextOnSecondary,
    tertiary = SecondaryLightBlue, // Puedes usar el mismo para terciario si no necesitas otro color
    onTertiary = TextOnSecondary,
    background = BackgroundLight,
    onBackground = TextDark,
    surface = SurfaceWhite,
    onSurface = TextDark,
    error = ErrorRed,
    onError = TextLight,
    // outline = ..., // Puedes definir un color para outlines si lo necesitas
    // inverseSurface = ...,
    // inverseOnSurface = ...,
    // scrim = ...,
    // surfaceBright = ...,
    // surfaceDim = ...,
    // surfaceContainer = ...,
    // surfaceContainerHigh = ...,
    // surfaceContainerHighest = ...,
    // surfaceContainerLow = ...,
    // surfaceContainerLowest = ...,
    // surfaceVariant = ...,
    // onSurfaceVariant = ...
)

// Esquema de colores para el tema oscuro (ejemplo básico, puedes ajustarlo)
private val AquaCycleDarkColorScheme = darkColorScheme(
    primary = PrimaryBlueVariant, // Un azul más oscuro para el primary en modo oscuro
    onPrimary = TextLight,
    primaryContainer = PrimaryBlue,
    onPrimaryContainer = TextLight,
    secondary = SecondaryLightBlue,
    onSecondary = TextOnSecondary,
    tertiary = SecondaryLightBlue,
    onTertiary = TextOnSecondary,
    background = Color(0xFF121212), // Un gris muy oscuro para el fondo en modo oscuro
    onBackground = TextLight,
    surface = Color(0xFF1E1E1E), // Un gris oscuro para superficies en modo oscuro
    onSurface = TextLight,
    error = ErrorRed,
    onError = TextLight
)


// --- Tipografía ---
// Usaremos la fuente predeterminada de Material Theme que generalmente es Roboto,
// pero puedes especificar una FontFamily si quieres una personalizada.
// Por ejemplo: val Montserrat = FontFamily(Font(R.font.montserrat_regular))
// Asegúrate de añadir la fuente a tu carpeta 'res/font'.

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
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium, // Un poco más de peso para títulos medios
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
        fontWeight = FontWeight.Medium, // Para botones y elementos interactivos
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
    small = RoundedCornerShape(4.dp),    // Pequeños radios para elementos como botones pequeños
    medium = RoundedCornerShape(8.dp),   // Radios medianos para tarjetas o dialogos
    large = RoundedCornerShape(12.dp)    // Radios ligeramente más grandes para contenedores principales
)


// --- Función Composable para aplicar el Tema ---
@Composable
fun AquaCycleTheme(
    darkTheme: Boolean = false, // Puedes detectar si el sistema usa dark mode con isSystemInDarkTheme()
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