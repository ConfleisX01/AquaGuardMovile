package com.example.aquaguard.data.config

import android.content.Context
import com.example.aquaguard.data.models.Usuario

class SessionManager(private val context: Context) {
    private val prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE)

    fun guardarUsuario(id: Int) {
        prefs.edit().putInt("id_usuario", id).apply()
    }

    fun obtenerUsuario(): Int {
        return prefs.getInt("id_usuario", -1)
    }

    fun saveUser(usuario: Usuario) {
        prefs.edit()
            .putInt("id", usuario.id)
            .putString("nombre", usuario.nombre)
            .putString("correo", usuario.correo)
            .apply()
    }

    fun getUser(): Usuario? {
        val id = prefs.getInt("id", -1)
        val nombre = prefs.getString("nombre", null)
        val correo = prefs.getString("correo", null)

        return if (id != -1 && nombre != null && correo != null) {
            Usuario(id, nombre, "", "", "", "", "", correo, "")
        } else null
    }

    fun clear() {
        prefs.edit().clear().apply()
    }
}
