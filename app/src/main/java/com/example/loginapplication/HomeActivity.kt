package com.example.loginapplication

import DataBaseUsers.DataBaseHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
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

        val recyclreview = findViewById<RecyclerView>(R.id.rv_usuarios)
        val usuariolista = ArrayList<Usuario>()

        if(cursor?.moveToFirst()!!){
            do{

                val nombre = (cursor.getString(0).toString())
                val apellido = (cursor.getString(1).toString())
                val email = (cursor.getString(2).toString())
                val telefono = (cursor.getString(3).toString())

                val user = Usuario(nombre,apellido,email,telefono)

                usuariolista.add(user)

            }while (cursor.moveToNext())


            recyclreview.layoutManager = LinearLayoutManager(this)
            recyclreview.adapter = UsuarioAdapter(usuariolista)


        }else {
            Toast.makeText(this,"No se pudo consultar ERROR",Toast.LENGTH_SHORT).show()
        }

    }


}