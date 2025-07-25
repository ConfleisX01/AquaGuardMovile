package com.example.aquaguard.ui.profile

import android.provider.Telephony
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aquaguard.data.ViewModel.UsuarioViewModel
import com.example.aquaguard.data.models.Usuario

@Composable
fun ProfileScreen(viewModel: UsuarioViewModel) {
    val usuario = viewModel.usuarioActual.value

    LaunchedEffect(Unit) {
        viewModel.obtenerUsuario(1)
        Log.i("DEBUG", "Pasando por launchedEffect")
    }

    var nombre by remember { mutableStateOf("") }
    var apellidoPaterno by remember { mutableStateOf("") }
    var apellidoMaterno by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var pais by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    LaunchedEffect(usuario) {
        usuario?.let {
            nombre = it.nombre
            apellidoPaterno = it.apellidoPaterno
            apellidoMaterno = it.apellidoMaterno
            telefono = it.telefono
            fechaNacimiento = it.fechaNacimiento
            pais = it.pais
            correo = it.correo
            contrasena = it.contrasena
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text("Editar perfil", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = apellidoPaterno,
            onValueChange = { apellidoPaterno = it },
            label = { Text("Apellido Paterno") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = apellidoMaterno,
            onValueChange = { apellidoMaterno = it },
            label = { Text("Apellido Materno") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = fechaNacimiento,
            onValueChange = { fechaNacimiento = it },
            label = { Text("Fecha de Nacimiento") },
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = pais,
            onValueChange = { pais = it },
            label = { Text("Pais") },
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = {
                modificarUsuario(
                    nombre, apellidoPaterno, apellidoMaterno, telefono, fechaNacimiento, pais, correo, contrasena, viewModel
                )
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Guardar Cambios")
        }
    }
}

// Funcion para actualizar el usuario usando el View Model de usuarios
fun modificarUsuario(
    nombre: String,
    apellidoPaterno: String,
    apellidoMaterno: String,
    telefono: String,
    fechaNacimiento: String,
    pais: String,
    correo: String,
    contrasena: String,
    viewModel: UsuarioViewModel
) {
    val usuarioActualizado = Usuario (
        id = 1,
        nombre = nombre,
        apellidoPaterno = apellidoPaterno,
        apellidoMaterno = apellidoMaterno,
        telefono = telefono,
        fechaNacimiento = fechaNacimiento,
        pais = pais,
        correo = correo,
        contrasena = contrasena
    )
    val response = viewModel.modificarUsuario(1, usuarioActualizado)
    Log.i("DEBUVAR", response.toString())
}