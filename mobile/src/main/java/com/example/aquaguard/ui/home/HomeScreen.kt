package com.example.aquaguard.ui.home

import android.content.Context
import android.location.Location
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.aquaguard.R
import com.example.aquaguard.data.config.SessionManager
import com.example.aquaguard.data.models.Usuario
import com.example.compose.AquaCycleTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import java.time.LocalTime

@Composable
//@Preview(showBackground = true)
fun HomeScreen (
    viewModel: HomeViewModel,
    onLogout: () -> Unit,
    context: Context,
) {
    AquaCycleTheme {
        Column (
            modifier = Modifier
                .fillMaxSize()
        ) {
            //TopAppBarTitle()
            WeatherInfoSection()
            DeviceCardInfo()
        }
    }
}

@Composable
fun TopAppBarTitle () {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Text(
            "Información del clima",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun WeatherInfoSection () {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column (
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.sun),
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
            Text("Soleado")
        }

        Column (
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("28", style = MaterialTheme.typography.titleLarge)
            Text("Temperatura")
        }

        Column (
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("77%", style = MaterialTheme.typography.titleLarge)
            Text("Precipitación")
        }
    }
}

@Composable
fun DeviceCardInfo () {
    Column (
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(color = MaterialTheme.colorScheme.onBackground)
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        DeviceCardWeatherInfoCard()
        Text("Información de los almacenes", color = MaterialTheme.colorScheme.onSecondary)
        DeviceCardWaterInfo()
        Text("Información de la agua en los almacenes", color = MaterialTheme.colorScheme.onSecondary)
    }
}

@Composable
fun DeviceCardWeatherInfoCard () {
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
        ),
        modifier  = Modifier
            .fillMaxWidth()
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("A recolectar", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
            Text("Buen momento para recolectar agua", color = MaterialTheme.colorScheme.onBackground)
        }
    }
}

@Composable
fun DeviceCardWaterInfo () {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column (
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(color = MaterialTheme.colorScheme.onSecondary)
                .padding(12.dp)
                .weight(.5f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.sun),
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
            Text("Agua sin filtrar")
            Text("90%", style = MaterialTheme.typography.titleLarge)
        }

        Spacer(modifier = Modifier.weight(.1f))

        Column (
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(color = MaterialTheme.colorScheme.onSecondary)
                .padding(12.dp)
                .weight(.5f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.sun),
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
            Text("Agua filtrada")
            Text("10%", style = MaterialTheme.typography.titleLarge)
        }
    }
}

//@Composable
//fun HomeScreen(
//    viewModel: HomeViewModel,
//    onLogout: () -> Unit,
//    context: Context,
//) {
//    val scrollState = rememberScrollState()
//    var idUsuario: Int = 0
//    val formState by viewModel.formState.collectAsState()
//
//    LaunchedEffect(Unit) {
//        idUsuario = SessionManager(context).obtenerUsuario()
//        viewModel.cargarUsuario(idUsuario)
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(scrollState)
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        HeaderWelcome(formState, onLogout)
//        WeatherCard()
//        Row (
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            DeviceInformationCard(
//                modifier = Modifier.weight(1f),
//                title = "Agua almacenada",
//                information = "80%"
//            )
//            DeviceInformationCard(
//                modifier = Modifier.weight(1f),
//                title = "Agua filtrada",
//                information = "40%"
//            )
//        }
//        DeviceInformationCardContainer("Información del agua almacenada", "Mal pH", "18", "Medio", type = false)
//        DeviceInformationCardContainer("Información del agua filtrada", "Buen pH", "22", "Bien", type = true)
//    }
//}
//
//@Composable
//fun DeviceInformationCard(modifier: Modifier = Modifier, title: String, information: String) {
//    Card (
//        modifier = modifier
//            .height(120.dp),
//        shape = RoundedCornerShape(8.dp),
//        elevation = CardDefaults.cardElevation(4.dp),
//    ) {
//        Box (
//            modifier = Modifier
//                .background(color = MaterialTheme.colorScheme.primary)
//                .padding(16.dp)
//                .fillMaxSize()
//        ) {
//            Column (
//                modifier = Modifier
//                    .fillMaxSize()
//            ) {
//                Text(
//                    title,
//                    style = MaterialTheme.typography.bodySmall,
//                    color = MaterialTheme.colorScheme.onPrimary
//                )
//                Row (
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    horizontalArrangement = Arrangement.Center,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(information,
//                        style = MaterialTheme.typography.displaySmall,
//                        color = MaterialTheme.colorScheme.onPrimary
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun DeviceInformationCardContainer(title: String, ph: String, temperature: String, clean: String, type: Boolean) {
//    val backgroundImage = if (type) {
//        R.drawable.card_1_background   // fondo azul con decoraciones suaves
//    } else {
//        R.drawable.card_4_background // fondo gris
//    }
//
//    Card (
//        modifier = Modifier
//            .height(120.dp)
//            .fillMaxWidth(),
//        shape = RoundedCornerShape(8.dp),
//        elevation = CardDefaults.cardElevation(4.dp),
//    ) {
//        Box (
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            Image(
//                painter = painterResource(id = backgroundImage),
//                contentDescription = null,
//                contentScale = ContentScale.FillBounds,
//                modifier = Modifier.fillMaxSize()
//            )
//
//            Column (
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(12.dp)
//            ) {
//                Row (
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        title,
//                        style = MaterialTheme.typography.titleMedium,
//                        color = Color.White
//                    )
//                }
//
//                Row (
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .fillMaxHeight(),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Column (
//                        modifier = Modifier,
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.ph_icon),
//                            contentDescription = "PH",
//                            modifier = Modifier.size(32.dp)
//                        )
//                        Text(
//                            text = ph,
//                            style = MaterialTheme.typography.bodyMedium,
//                            color = Color.White
//                        )
//                    }
//                    Column (
//                        modifier = Modifier,
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.temperature_icon),
//                            contentDescription = "Temperatura",
//                            modifier = Modifier.size(32.dp)
//                        )
//                        Text(
//                            text = "${temperature}°",
//                            style = MaterialTheme.typography.bodyMedium,
//                            color = Color.White
//                        )
//                    }
//                    Column (
//                        modifier = Modifier,
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.water_glass_icon),
//                            contentDescription = "Turbidez",
//                            modifier = Modifier.size(32.dp)
//                        )
//                        Text(
//                            text = clean,
//                            style = MaterialTheme.typography.bodyMedium,
//                            color = Color.White
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalPermissionsApi::class)
//@Composable
//fun WeatherCard(viewModel: HomeViewModel = viewModel()) {
//    val weather by viewModel.weather.collectAsState()
//
//    val context = LocalContext.current
//    val permissionState = rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)
//
//    val fusedLocationClient = remember {
//        LocationServices.getFusedLocationProviderClient(context)
//    }
//
//    LaunchedEffect(permissionState.status.isGranted) {
//        if (permissionState.status.isGranted) {
//            try {
//                fusedLocationClient.lastLocation
//                    .addOnSuccessListener { location ->
//                        location?.let {
//                            viewModel.fetchWeather(it.latitude, it.longitude)
//                        }
//                    }
//            } catch (e: SecurityException) {
//                e.printStackTrace()
//            }
//        } else {
//            permissionState.launchPermissionRequest()
//        }
//    }
//
//    weather?.let { data ->
//        Card(
//            shape = RoundedCornerShape(8.dp),
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Box(
//                modifier = Modifier
//                    .background(getWeatherGradient(data.iconCode))
//                    .padding(16.dp)
//            ) {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center,
//                        modifier = Modifier.weight(0.5f)
//                    ) {
//                        Image(
//                            painter = painterResource(id = getWeatherIcon(data.iconCode)),
//                            contentDescription = data.description,
//                            modifier = Modifier.size(48.dp)
//                        )
//                        Text(data.description, style = MaterialTheme.typography.bodyLarge)
//                        Text("${data.temperature}°", style = MaterialTheme.typography.bodySmall)
//                    }
//
//                    Spacer(modifier = Modifier.width(32.dp))
//
//                    Column(
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.Start,
//                        modifier = Modifier.weight(1f)
//                    ) {
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically,
//                            modifier = Modifier.padding(bottom = 4.dp)
//                        ) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.ic_humidity),
//                                contentDescription = "Humedad",
//                                modifier = Modifier.size(20.dp),
//                                tint = Color.Unspecified
//                            )
//                            Spacer(modifier = Modifier.width(8.dp))
//                            Text("Humedad: ${data.humidity}%", style = MaterialTheme.typography.bodyMedium)
//                        }
//
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically,
//                            modifier = Modifier.padding(bottom = 4.dp)
//                        ) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.ic_rain),
//                                contentDescription = "Precipitación",
//                                modifier = Modifier.size(20.dp),
//                                tint = Color.Unspecified
//                            )
//                            Spacer(modifier = Modifier.width(8.dp))
//                            Text("Precipitación: ${data.precipitation}%", style = MaterialTheme.typography.bodyMedium)
//                        }
//
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.ic_cloud),
//                                contentDescription = "Nubosidad",
//                                modifier = Modifier.size(20.dp),
//                                tint = Color.Unspecified
//                            )
//                            Spacer(modifier = Modifier.width(8.dp))
//                            Text("Nubosidad: ${data.cloudcover}%", style = MaterialTheme.typography.bodyMedium)
//                        }
//                    }
//                }
//            }
//        }
//    } ?: run {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            CircularProgressIndicator()
//        }
//    }
//}
//
//@Composable
//fun HeaderWelcome(
//    formState: Usuario,
//    onLogout: () -> Unit
//) {
//    val saludo = when (LocalTime.now().hour) {
//        in 5..11 -> "☀️ Buenos días"
//        in 12..17 -> "🌤️ Buenas tardes"
//        else -> "🌙 Buenas noches"
//    }
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 16.dp, vertical = 8.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Column {
//            Text(
//                text = "$saludo, ${formState.nombre} 👋",
//                style = MaterialTheme.typography.titleMedium,
//                color = MaterialTheme.colorScheme.onPrimary
//            )
//            Spacer(modifier = Modifier.height(2.dp))
//            Text(
//                text = "Revisemos cómo está tu día",
//                style = MaterialTheme.typography.bodySmall,
//                color = MaterialTheme.colorScheme.onBackground
//            )
//        }
//
//        OutlinedButton(
//            onClick = { onLogout() },
//            shape = RoundedCornerShape(12.dp),
//            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
//            colors = ButtonDefaults.outlinedButtonColors(
//                contentColor = MaterialTheme.colorScheme.primary
//            ),
//            contentPadding = PaddingValues(8.dp)
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.logout_icon),
//                contentDescription = "Cerrar sesión",
//                modifier = Modifier.size(20.dp),
//                tint = MaterialTheme.colorScheme.primary
//            )
//        }
//    }
//}
//
//@Composable
//fun getWeatherGradient(weatherCode: Int): Brush {
//    return when (weatherCode) {
//        0 -> Brush.verticalGradient( // Soleado
//            colors = listOf(
//                Color(0xFFFFF176), // Amarillo claro
//                Color(0xFFFFA726)  // Naranja suave
//            )
//        )
//        in 1..3 -> Brush.verticalGradient( // Parcialmente nublado
//            colors = listOf(
//                Color(0xFFB0BEC5), // Gris azulado
//                Color(0xFFECEFF1)
//            )
//        )
//        in 45..48 -> Brush.verticalGradient( // Neblina
//            colors = listOf(
//                Color(0xFF90A4AE),
//                Color(0xFFCFD8DC)
//            )
//        )
//        in 51..67 -> Brush.verticalGradient( // Llovizna
//            colors = listOf(
//                Color(0xFF80D8FF),
//                Color(0xFF40C4FF)
//            )
//        )
//        in 80..82 -> Brush.verticalGradient( // Lluvia
//            colors = listOf(
//                Color(0xFF4FC3F7),
//                Color(0xFF0288D1)
//            )
//        )
//        in 95..99 -> Brush.verticalGradient( // Tormenta
//            colors = listOf(
//                Color(0xFF455A64),
//                Color(0xFF263238)
//            )
//        )
//        else -> Brush.verticalGradient( // Desconocido
//            colors = listOf(
//                MaterialTheme.colorScheme.primaryContainer,
//                MaterialTheme.colorScheme.secondaryContainer
//            )
//        )
//    }
//}
//
//fun getWeatherIcon(weatherCode: Int): Int {
//    return when (weatherCode) {
//        0 -> R.drawable.sun                      // Despejado
//        1, 2, 3 -> R.drawable.cloudy      // Parcialmente nublado
//        in 45..48 -> R.drawable.fog              // Neblina
//        in 51..67 -> R.drawable.light_rain          // Llovizna
//        in 80..82 -> R.drawable.rain             // Lluvia
//        in 95..99 -> R.drawable.storm            // Tormenta
//        else -> R.drawable.logo       // Icono por defecto
//    }
//}