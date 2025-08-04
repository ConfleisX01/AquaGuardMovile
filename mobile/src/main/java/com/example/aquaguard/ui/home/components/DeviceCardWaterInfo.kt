package com.example.aquaguard.ui.home.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.aquaguard.R

@Composable
fun WaveWithBackground(
    modifier: Modifier = Modifier,
    progress: Float, // 0f a 1f
    backgroundGradient: Brush,
    waveColor: Color,
    amplitude: Float = 10f,
    frequency: Float = 1.5f
) {
    val infiniteTransition = rememberInfiniteTransition(label = "wave")
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2 * Math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing)
        ),
        label = "wavePhase"
    )

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val waterLevelY = height * (1 - progress)

        // Ola sobre el fondo
        val path = Path().apply {
            moveTo(0f, waterLevelY)
            for (x in 0..width.toInt()) {
                val y = amplitude * kotlin.math.sin((x / width * frequency * 2 * Math.PI + phase)).toFloat()
                lineTo(x.toFloat(), waterLevelY + y)
            }
            lineTo(width, height)
            lineTo(0f, height)
            close()
        }

        drawPath(path = path, color = waveColor)
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun DeviceCardWaterInfo(
    storeFilterWater: Float = 0.0f,
    storeNoFilterWater: Float = 0.0f
) {
    val animatedNoFilterHeight by animateFloatAsState(
        targetValue = storeNoFilterWater / 100f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "animatedNoFilterHeight"
    )

    val animatedFilterHeight by animateFloatAsState(
        targetValue = storeFilterWater / 100f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "animatedFilterHeight"
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Agua sin filtrar
        OutlinedCard(
            modifier = Modifier
                .weight(0.5f)
                .height(120.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                // Fondo animado (nivel de agua)
                WaveWithBackground(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.BottomCenter),
                    progress = animatedNoFilterHeight,
                    backgroundGradient = Brush.verticalGradient(
                        colors = listOf(Color(0xFF00B4D8), Color(0xFF0077B6))
                    ),
                    waveColor = Color(0xFF0077B6)
                )

                // Contenido
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_ph),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                    Text("Agua sin filtrar", color = MaterialTheme.colorScheme.onSurface)
                    Text(
                        String.format("%.2f%%", storeNoFilterWater),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Agua filtrada
        OutlinedCard(
            modifier = Modifier
                .weight(0.5f)
                .height(120.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                WaveWithBackground(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.BottomCenter),
                    progress = animatedFilterHeight,
                    backgroundGradient = Brush.verticalGradient(
                        colors = listOf(Color(0xFF00B4D8), Color(0xFF0077B6))
                    ),
                    waveColor = Color(0xFF00B4D8)
                )

                // Contenido
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_ph),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                    Text("Agua filtrada", color = MaterialTheme.colorScheme.onSurface)
                    Text(
                        String.format("%.2f%%", storeFilterWater),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
