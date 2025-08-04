package com.example.aquaguard.ui.profile.edit

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aquaguard.data.config.SessionManager
import com.example.aquaguard.data.models.Usuario
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileEditScreen(viewModel: ProfileEditViewModel = viewModel(), context: Context) {
    val formState by viewModel.formState.collectAsState()
    val sessionManager = remember { SessionManager(context) }
    val usuarioSesionId = sessionManager.obtenerUsuario()

    val snackbarHostState = remember { SnackbarHostState() }

    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.cargarUsuario(usuarioSesionId)
    }

    LaunchedEffect(Unit) {
        viewModel.mensajeExito.collectLatest { mensaje ->
            snackbarHostState.showSnackbar(mensaje)
        }
    }

    Scaffold (
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background, MaterialTheme.colorScheme.surface
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
                ProfileCard(formState)

                Campo("Nombre", formState.nombre, "nombre", keyboardType = KeyboardType.Text) {
                    viewModel.actualizarCampo(
                        it.first,
                        it.second
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Campo(
                        "Apellido Paterno",
                        formState.apellidoPaterno,
                        "apellidoPaterno",
                        Modifier.weight(1f),
                        keyboardType = KeyboardType.Text
                    ) {
                        viewModel.actualizarCampo(it.first, it.second)
                    }
                    Campo(
                        "Apellido Materno",
                        formState.apellidoMaterno,
                        "apellidoMaterno",
                        Modifier.weight(1f),
                        keyboardType = KeyboardType.Text
                    ) {
                        viewModel.actualizarCampo(it.first, it.second)
                    }
                }

                Campo(
                    "Teléfono",
                    formState.telefono,
                    "telefono",
                    keyboardType = KeyboardType.Phone,
                ) { viewModel.actualizarCampo(it.first, it.second) }

                Campo(
                    "Fecha de nacimiento",
                    formState.fechaNacimiento,
                    "fechaNacimiento",
                    keyboardType = KeyboardType.Text,
                ) { viewModel.actualizarCampo(it.first, it.second) }

                Campo("País", formState.pais, "pais", keyboardType = KeyboardType.Text) {
                    viewModel.actualizarCampo(
                        it.first,
                        it.second
                    )
                }

                Campo("Correo electrónico", formState.correo, "correo", keyboardType = KeyboardType.Email) {
                    viewModel.actualizarCampo(
                        it.first,
                        it.second
                    )
                }
                Campo("Contraseña", "", "contrasena", keyboardType = KeyboardType.Password) {
                    viewModel.actualizarCampo(
                        it.first,
                        it.second
                    )
                }

                Button(
                    onClick = { viewModel.guardarCambios() },
                    modifier = Modifier
                        .align(Alignment.End)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(50.dp),
                    contentPadding = ButtonDefaults.TextButtonContentPadding
                ) {
                    Text("Guardar Cambios")
                }
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
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: ((Pair<String, String>) -> Unit)? = null
) {
    OutlinedTextField(
        value = valor,
        onValueChange = { onValueChange?.invoke(Pair(campo, it)) },
        label = { Text(label) },
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

@Composable
fun ProfileCard(usuarioLogueado : Usuario) {
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
            var firstLetter = usuarioLogueado.nombre.trim().firstOrNull()?.uppercaseChar() ?: '?'
            Text(firstLetter.toString(), color = MaterialTheme.colorScheme.onPrimary)
        }
        Column(
            modifier = Modifier
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val fullName = "${usuarioLogueado.nombre} ${usuarioLogueado.apellidoPaterno} ${usuarioLogueado.apellidoMaterno}"
            Text(fullName, style = MaterialTheme.typography.bodyLarge)
            Text("Usuario", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary)
        }
    }
}