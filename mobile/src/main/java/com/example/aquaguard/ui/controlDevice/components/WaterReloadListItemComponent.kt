package com.example.aquaguard.ui.controlDevice.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aquaguard.data.models.TanqueEnum
import java.time.LocalDate
import com.example.aquaguard.R
import java.nio.file.WatchEvent

@Composable
fun WaterReloadListItemComponent(
    tank: Int = 0,
    temperature: Float = 0.0f,
    tds: Int = 0,
    ph: Float = 0.0f,
    quantity: Float = 0.0f,
    lastUpdate: LocalDate = LocalDate.now()
) {
    val shape = RoundedCornerShape(8.dp)

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .border(BorderStroke(1.dp, color = MaterialTheme.colorScheme.outline), shape)
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(12.dp),
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(12.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_waterqa),
                contentDescription = "Card icon",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        Column (
            modifier = Modifier
                .weight(1f)
                .padding(8.dp, 0.dp)
        ) {
            Text("${lastUpdate}")
            Text("${tank}")
        }
        Row (
            modifier = Modifier
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("$quantity L")
        }
    }
}