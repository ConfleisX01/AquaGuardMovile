package com.example.aquaguard.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aquaguard.data.ViewModel.UsuarioViewModel
import androidx.compose.foundation.lazy.items

@Composable
fun HomeScreen(viewModel: UsuarioViewModel) {
    Log.i("DEBUG", "Cargando la seccion de home")
    
    LaunchedEffect(Unit) {
        viewModel.cargarUsuarios()
        Log.i("DEBUG", "Pasando por launchedEffect")
    }

    val usuarios = viewModel.usuarios


    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LazyColumn {
            items(usuarios) { usuario ->
                Text(text = "${usuario.nombre}")
            }
        }
    }
}