package com.example.aquaguard.ui.register

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aquaguard.data.ViewModel.UsuarioViewModel
import com.example.aquaguard.data.models.Usuario

@Preview
@Composable
fun RegisterScreen(usuarioViewModel: UsuarioViewModel = viewModel()) {
    var nombre by remember { mutableStateOf("") }
    var apellidoPaterno by remember { mutableStateOf("") }
    var apellidoMaterno by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var pais by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmarContrasena by remember { mutableStateOf("") }
    var aceptaTerminos by remember { mutableStateOf(false) }
    var mensajeExito by remember { mutableStateOf<String?>(null) }

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Registro de usuario", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = apellidoPaterno, onValueChange = { apellidoPaterno = it }, label = { Text("Apellido Paterno") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = apellidoMaterno, onValueChange = { apellidoMaterno = it }, label = { Text("Apellido Materno") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = telefono, onValueChange = { telefono = it }, label = { Text("Teléfono") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        OutlinedTextField(value = fechaNacimiento, onValueChange = { fechaNacimiento = it }, label = { Text("Fecha de Nacimiento") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = pais, onValueChange = { pais = it }, label = { Text("País") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo electrónico") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        OutlinedTextField(value = contrasena, onValueChange = { contrasena = it }, label = { Text("Contraseña") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        OutlinedTextField(value = confirmarContrasena, onValueChange = { confirmarContrasena = it }, label = { Text("Confirmar Contraseña") }, modifier = Modifier.fillMaxWidth(), singleLine = true)

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = aceptaTerminos, onCheckedChange = { aceptaTerminos = it })
            Text("Aceptar términos y condiciones")
        }

        Button(
            onClick = {

                if (nombre.isBlank() || apellidoPaterno.isBlank() || correo.isBlank() || contrasena.isBlank()) {
                    val mensaje = "Completa los campos obligatorios"
                    usuarioViewModel.errorMessage = mensaje
                    Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
                    Log.d("REGISTRO", mensaje)
                    return@Button
                }

                if (contrasena != confirmarContrasena) {
                    val mensaje = "Las contraseñas no coinciden"
                    usuarioViewModel.errorMessage = mensaje
                    Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
                    Log.d("RegisterScreen", mensaje)
                    return@Button
                }

                if (!aceptaTerminos) {
                    val mensaje = "Debes aceptar los términos y condiciones"
                    usuarioViewModel.errorMessage = mensaje
                    Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
                    Log.d("RegisterScreen", mensaje)
                    return@Button
                }

                val nuevoUsuario = Usuario(
                    id = 0,
                    nombre = nombre,
                    apellidoPaterno = apellidoPaterno,
                    apellidoMaterno = apellidoMaterno,
                    telefono = telefono,
                    fechaNacimiento = fechaNacimiento,
                    pais = pais,
                    correo = correo,
                    contrasena = contrasena
                )

                usuarioViewModel.agregarUsuario(nuevoUsuario)
                mensajeExito = "Registro exitoso"
                Toast.makeText(context, mensajeExito!!, Toast.LENGTH_SHORT).show()
                Log.d("REGISTRO", "Usuario registrado: $nuevoUsuario")

                nombre = ""
                apellidoPaterno = ""
                apellidoMaterno = ""
                telefono = ""
                fechaNacimiento = ""
                pais = ""
                correo = ""
                contrasena = ""
                confirmarContrasena = ""
                aceptaTerminos = false
            },
            enabled = aceptaTerminos,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Registrarse")
        }

        usuarioViewModel.errorMessage?.let {
            Text(text = it, color = Color.Red)
        }

        mensajeExito?.let {
            Text(text = it, color = Color.Green)
        }
    }
}
