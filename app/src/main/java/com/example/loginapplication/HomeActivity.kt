package com.example.loginapplication

import android.content.Intent
import com.example.loginapplication.DataBaseUsers.DataBaseHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapplication.Objects.Usuario
import com.example.loginapplication.adapter.UsuarioAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var accesoDataBaseHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        accesoDataBaseHelper = DataBaseHelper(this)

        ConsultarDatosUsuario()
    }

    private fun ConsultarDatosUsuario() {

        val cursor = accesoDataBaseHelper.getData()

        val recyclreDatosUSuario = findViewById<RecyclerView>(R.id.rv_usuarios)
        val usuariolista = ArrayList<Usuario>()
        val manager = LinearLayoutManager(this)

        if (cursor?.moveToFirst()!!) {
            do {

                val id = (cursor.getInt(0))
                val nombre = (cursor.getString(1).toString())
                val apellido = (cursor.getString(2).toString())
                val email = (cursor.getString(3).toString())
                val telefono = (cursor.getString(4).toString())

                val user = Usuario(id,nombre, apellido, email, telefono)

                usuariolista.add(user)

            } while (cursor.moveToNext())


            recyclreDatosUSuario.layoutManager = manager

            recyclreDatosUSuario.adapter = UsuarioAdapter(usuariolista) {
                    usuario -> onItemSelected(usuario) } // funcion landa


        } else {
            Toast.makeText(this, "No se pudo consultar ERROR", Toast.LENGTH_SHORT).show()
        }

    }


    fun onItemSelected(usuario: Usuario) {

        Toast.makeText(this, usuario.id.toString() + usuario.userNombre, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this,DetalleUsuarioActivity::class.java).putExtra("id", usuario.id))
    }


}