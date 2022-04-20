package com.example.loginapplication

import DataBaseUsers.DataBaseHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class HomeActivity : AppCompatActivity() {

    private lateinit var accesoDataBaseHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        accesoDataBaseHelper = DataBaseHelper(this)

        val btnConsultar = findViewById<Button>(R.id.btn_home_consultar)

        btnConsultar.setOnClickListener {
            ConsultarDatosUsuario()
        }


    }

    private fun ConsultarDatosUsuario() {

        val cursor = accesoDataBaseHelper.getData()

        val txvnombre = findViewById<TextView>(R.id.txv_home_name)
        val txvapellido = findViewById<TextView>(R.id.txv_home_apellido)
        val txvemail = findViewById<TextView>(R.id.txv_home_email)
        val txvtell= findViewById<TextView>(R.id.txv_home_tell)

        if(cursor?.moveToFirst()!!){
            do{
                txvnombre.append(cursor.getString(1).toString())
                txvapellido.append(cursor.getString(2).toString())
                txvemail.append(cursor.getString(3).toString())
                txvtell.append(cursor.getString(4).toString() + "\n")
            }while (cursor.moveToNext())

        }

    }


}