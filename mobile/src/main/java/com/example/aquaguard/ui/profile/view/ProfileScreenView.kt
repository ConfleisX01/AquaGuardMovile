package com.example.aquaguard.ui.profile.view

import android.content.Context
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.Navigator
import androidx.navigation.navOptions
import com.example.aquaguard.data.config.SessionManager
import com.example.aquaguard.data.models.Usuario
import com.example.aquaguard.ui.navigation.Screen
import com.example.aquaguard.ui.theme.AquaGuardTheme
import com.google.android.gms.common.internal.Objects

@Composable
fun ProfileInformationScreen (
    navController: NavController,
) {
    val viewModel: ProfileScreenViewModel = viewModel()
    val context = LocalContext.current
    var visible by remember { mutableStateOf(true) }
    val density = LocalDensity.current

    val sessionManager = remember { SessionManager(context) }
    val usuarioSesionId = sessionManager.obtenerUsuario()
    val usuarioCargado by viewModel.formState.collectAsState()// formState

    val isLoadedData by viewModel.isLoadedData.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarUsuario(usuarioSesionId)
    }

    if (isLoadedData) {
        AquaGuardTheme {
            AnimatedVisibility(
                visible = visible,
                enter = slideInVertically {
                    with(density) { -40.dp.roundToPx() }
                } + expandVertically(
                    expandFrom = Alignment.Top
                ) + fadeIn(
                    initialAlpha = 0.3f
                ),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) { }
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileCardViewer(usuarioCargado!!)
                Text(
                    "Hola, " + usuarioCargado!!.nombre.toString(),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge
                )
                ProfileInformationViewer(navController, usuarioCargado!!)
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun ProfileCardViewer (usuarioLogueado : Usuario) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {
        Image(
            painter = painterResource(id = com.example.aquaguard.R.drawable.background_2),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                val firstLetter = usuarioLogueado.nombre.trim().firstOrNull()?.uppercaseChar() ?: '?'
                Text(firstLetter.toString(), color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.displaySmall)
            }
        }
    }
}

@Composable
fun ProfileInformationViewer (navController: NavController, usuarioLogueado : Usuario) {
    val datosUsuario = mapOf(
        "Nombre" to usuarioLogueado.nombre,
        "Apellido paterno" to usuarioLogueado.apellidoPaterno,
        "Apellido materno" to usuarioLogueado.apellidoMaterno,
        "Fecha de nacimiento" to usuarioLogueado.fechaNacimiento,
        "Teléfono" to usuarioLogueado.telefono,
        "Correo" to usuarioLogueado.correo,
        "País" to usuarioLogueado.pais,
        "Contraseña" to "*****"
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        datosUsuario.forEach { (label, value) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            HorizontalDivider(thickness = 1.dp)
        }
        Column {
            Button(
                onClick = {
                    navController.navigate(Screen.ProfileEdit.route)
                },
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text("Editar Perfil")
            }
            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Cerrar Sesión")
            }
        }
    }
}