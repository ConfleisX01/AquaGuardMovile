package com.example.aquaguard.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val AquaGuardLightColorScheme = lightColorScheme(
    primary = SkyBlue,             // Botones, enlaces principales
    onPrimary = White,

    primaryContainer = LightBlue,  // Hover / variantes
    onPrimaryContainer = DeepBlue,

    secondary = GrayText,          // Texto secundario
    onSecondary = White,

    tertiary = AquaBlue,           // Acento extra
    onTertiary = DeepBlue,

    background = SoftWhite,        // Fondo general
    onBackground = DeepBlue,

    surface = White,               // Tarjetas / formularios
    onSurface = DeepBlue,

    outline = GrayBorder,          // Bordes, separadores

    surfaceVariant = LightSurface
)

private val AquaGuardDarkColorScheme = darkColorScheme(
    primary = SkyBlue,             // Botón, enlaces
    onPrimary = White,

    primaryContainer = LightBlue,  // Variante
    onPrimaryContainer = DeepBlue,

    secondary = GrayBorder,        // Texto claro
    onSecondary = DeepBlue,

    tertiary = AquaBlue,
    onTertiary = DeepBlue,

    background = DeepBlue,         // Fondo principal
    onBackground = White,

    surface = DarkSurface,         // Tarjetas / bloques
    onSurface = White,

    outline = DarkSurfaceVariant,  // Inputs y bordes

    surfaceVariant = DarkSurfaceVariant
)

@Composable
fun AquaGuardTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        AquaGuardDarkColorScheme
    } else {
        AquaGuardLightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(), // Puedes personalizarlo si usas fuentes como Poppins, etc.
        shapes = Shapes(),
        content = content
    )
}
