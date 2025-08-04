package com.example.aquaguard.ui.controlDevice.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp

@Composable
fun ChangeValveComponent(valveStateValue: Int?, onValveClick: (Int) -> Unit, type: String) {
    val targetColor = if (valveStateValue == 1) Color(0xFF00B4D8) else Color(0xFF0077B6)

    val animatedColor by animateColorAsState(
        targetValue = targetColor,
        label = "ValveButtonColor"
    )

    Button(
        onClick = {
            onValveClick(if ((valveStateValue ?: 0) == 1) 0 else 1)
        },
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = animatedColor,
            contentColor = Color.White
        )
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                if (type == "FILTER") "Agua filtrada" else "Agua sin filtrar",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = if (valveStateValue == 1) "Válvula abierta" else "Válvula cerrada",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
