package com.example.app_m6.Model

import androidx.lifecycle.LiveData
import com.example.app_m6.Model.model.Usuario
import com.example.app_m6.Model.model.UsuarioDao

//responsabilidad exponer los datos para el View-Model
//el respositorio expone las acciones del dao o modelo  a las capas superiores (vista, vista modelo) seria como el controlador mvc o es una copia del dao

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    //este value va a contener toda la info de la bD todas las tareas
    val listAllUsuario: LiveData<List<Usuario>> = usuarioDao.getAllUsuario1()

    suspend fun insertUsuario(usuario: Usuario) {
        usuarioDao.insertUsuario(usuario)
    }

    suspend fun updateUsuario(usuario: Usuario) {
        usuarioDao.updateUsuario(usuario)
    }

    suspend fun deleteUsuario(usuario: Usuario) {
        usuarioDao.deleteOneUsuario(usuario)
    }
    suspend fun deleteAllUsuario() {
        usuarioDao.deleteAllUsuario()
    }

    suspend fun listAllUsuario(usuario: Usuario) {
        usuarioDao.getAllUsuario()
    }

}
