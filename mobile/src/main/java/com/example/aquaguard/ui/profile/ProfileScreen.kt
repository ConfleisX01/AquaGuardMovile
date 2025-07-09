package com.example.aquaguard.ui.profile

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.unit.dp
import com.example.aquaguard.data.config.SessionManager

@Composable
fun ProfileScreen(viewModel: ProfileViewModel, context: Context) {
    val formState by viewModel.formState.collectAsState()
    val sessionManager = remember { SessionManager(context) }
    val usuarioSesionId = sessionManager.obtenerUsuario()

    // Cargamos los datos del usuario cuando inicie la pantalla
    LaunchedEffect(Unit) {
        viewModel.cargarUsuario(usuarioSesionId)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Editar perfil", style = MaterialTheme.typography.headlineSmall)

        @Composable
        fun Campo(label: String, valor: String, campo: String) {
            OutlinedTextField(
                value = valor,
                onValueChange = { viewModel.actualizarCampo(campo, it) },
                label = { Text(label) },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Campo("Nombre", formState.nombre, "nombre")
        Campo("Apellido Paterno", formState.apellidoPaterno, "apellidoPaterno")
        Campo("Apellido Materno", formState.apellidoMaterno, "apellidoMaterno")
        Campo("Teléfono", formState.telefono, "telefono")
        Campo("Fecha de nacimiento", formState.fechaNacimiento, "fechaNacimiento")
        Campo("País", formState.pais, "pais")
        Campo("Correo electrónico", formState.correo, "correo")
        Campo("Contraseña", formState.contrasena, "contrasena")

        Button(
            onClick = { viewModel.guardarCambios() },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Guardar Cambios")
        }
    }
}

@Composable
fun ProfileScreenPreview() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Editar perfil", style = MaterialTheme.typography.headlineSmall)

        @Composable
        fun Campo(label: String, valor: String, campo: String) {
            OutlinedTextField(
                value = valor,
                onValueChange = { },
                label = { Text(label) },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Campo("Nombre", "Nombre", "nombre")
        Campo("Apellido Paterno", "Apellido Paterno", "apellidoPaterno")
        Campo("Apellido Materno", "Apellido Materno", "apellidoMaterno")
        Campo("Teléfono", "Telefono", "telefono")
        Campo("Fecha de nacimiento", "Fecha de Nacimiento", "fechaNacimiento")
        Campo("País", "Pais", "pais")
        Campo("Correo electrónico", "Correo", "correo")
        Campo("Contraseña", "Clave", "contrasena")

        Button(
            onClick = { Log.i("DEBUG", "Hola") },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Guardar Cambios")
        }
    }
}