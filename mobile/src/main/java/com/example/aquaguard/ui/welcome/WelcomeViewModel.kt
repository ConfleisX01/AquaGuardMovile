package com.example.aquaguard.ui.welcome

import android.util.Log
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {
    fun onLoginClicked() {
        Log.i("NAVDEB", "Navegando al login")
    }

    fun onRegisterClicked() {
        Log.i("NAVDEB", "Navegando al registro")
    }
}