package com.example.aquaguard.ui.profile

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

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
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ProfileCard()

        Text("Datos personales", style = MaterialTheme.typography.bodyMedium)

        Campo("Nombre", "Nombre", "nombre")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Campo(
                "Apellido Paterno", "Apellido Paterno", "apellidoPaterno",
                modifier = Modifier.weight(1f)
            )
            Campo(
                "Apellido Materno", "Apellido Materno", "apellidoMaterno",
                modifier = Modifier.weight(1f)
            )
        }

        Campo("Teléfono", "Teléfono", "telefono")
        Campo("Fecha de nacimiento", "Fecha de Nacimiento", "fechaNacimiento")
        Campo("País", "País", "pais")
        Campo("Correo electrónico", "Correo", "correo")
        Campo("Contraseña", "Clave", "contrasena")

        Button(
            onClick = { /* ... */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = MaterialTheme.shapes.medium,
        ) {
            Text("Guardar Cambios")
        }
    }
}

@Composable
fun Campo(
    label: String,
    valor: String,
    campo: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = valor,
        onValueChange = { /* TODO: manejar cambios de estado */ },
        label = { Text(label) },
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
//@Preview(showSystemUi = true)
fun ProfileCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(12.dp),
            )
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.primary)
                .align(Alignment.CenterVertically),
            contentAlignment = Alignment.Center
        ) {
            Text("J", color = MaterialTheme.colorScheme.onPrimary)
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 12.dp)
        ) {
            Text("Juan Pablo Perez Fernandez")
            Text(
                "Usuario",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}