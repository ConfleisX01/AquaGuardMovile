package com.example.aquaguard.ui.profile

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aquaguard.ui.theme.AquaCycleTheme

@Composable
fun ProfileScreen(viewModel: ProfileViewModel, context: Context) {
    val formState by viewModel.formState.collectAsState()
    val sessionManager = remember { com.example.aquaguard.data.config.SessionManager(context) }
    val usuarioSesionId = sessionManager.obtenerUsuario()

    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.cargarUsuario(usuarioSesionId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background( brush = Brush.verticalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.background,
                    MaterialTheme.colorScheme.surface
                )
            )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ProfileCard()

            Campo("Nombre", formState.nombre, "nombre") { viewModel.actualizarCampo(it.first, it.second) }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Campo("Apellido Paterno", formState.apellidoPaterno, "apellidoPaterno", Modifier.weight(1f)) {
                    viewModel.actualizarCampo(it.first, it.second)
                }
                Campo("Apellido Materno", formState.apellidoMaterno, "apellidoMaterno", Modifier.weight(1f)) {
                    viewModel.actualizarCampo(it.first, it.second)
                }
            }

            Campo("Teléfono", formState.telefono, "telefono") { viewModel.actualizarCampo(it.first, it.second) }
            Campo("Fecha de nacimiento", formState.fechaNacimiento, "fechaNacimiento") { viewModel.actualizarCampo(it.first, it.second) }
            Campo("País", formState.pais, "pais") { viewModel.actualizarCampo(it.first, it.second) }
            Campo("Correo electrónico", formState.correo, "correo") { viewModel.actualizarCampo(it.first, it.second) }
            Campo("Contraseña", formState.contrasena, "contrasena") { viewModel.actualizarCampo(it.first, it.second) }

            Button(
                onClick = { viewModel.guardarCambios() },
                modifier = Modifier.align(Alignment.End),
                shape = RoundedCornerShape(50.dp)
            ) {
                Text("Guardar Cambios")
            }
        }
    }
}

@Composable
fun Campo(
    label: String,
    valor: String,
    campo: String,
    modifier: Modifier = Modifier,
    onValueChange: ((Pair<String, String>) -> Unit)? = null
) {
    OutlinedTextField(
        value = valor,
        onValueChange = { onValueChange?.invoke(Pair(campo, it)) },
        label = { Text(label) },
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun ProfileCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Text("J", color = MaterialTheme.colorScheme.onPrimary)
        }
        Column(
            modifier = Modifier
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Juan Pablo Perez Fernandez", style = MaterialTheme.typography.bodyLarge)
            Text("Usuario", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    AquaCycleTheme {
        Box (modifier = Modifier
            .fillMaxSize()
            .background( brush = Brush.verticalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.background,
                    MaterialTheme.colorScheme.surface
                )
            )
        )
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProfileCard()

                Campo("Nombre", "Nombre", "nombre")

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Campo("Apellido Paterno", "Apellido Paterno", "apellidoPaterno", modifier = Modifier.weight(1f))
                    Campo("Apellido Materno", "Apellido Materno", "apellidoMaterno", modifier = Modifier.weight(1f))
                }

                Campo("Teléfono", "Teléfono", "telefono")
                Campo("Fecha de nacimiento", "Fecha de Nacimiento", "fechaNacimiento")
                Campo("País", "País", "pais")
                Campo("Correo electrónico", "Correo", "correo")
                Campo("Contraseña", "Clave", "contrasena")

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /* Preview */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Text("Guardar Cambios")
                }
            }
        }
    }
}