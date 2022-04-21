package com.example.loginapplication

import DataBaseUsers.DataBaseHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginMainActivity : AppCompatActivity() {

    private lateinit var registroDBHelper : DataBaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonLogin = findViewById<Button>(R.id.btn_login)
        val botonSingUp = findViewById<Button>(R.id.btn_singup)

        registroDBHelper = DataBaseHelper(this)

        botonLogin.setOnClickListener {
            validarLogin()
        }

        botonSingUp.setOnClickListener {
            registroUsuario()
        }


    }


     fun validarLogin() {

         val correo = findViewById<EditText>(R.id.et_Email)
         val contrasena = findViewById<EditText>(R.id.et_Password)

         val obtenerCorreo = correo.text.toString()
         val obtenerContrasena = contrasena.text.toString()

         var correoUsu : String = ""
         var passUsu : String = ""

         val cursor = registroDBHelper.LoguinData(obtenerCorreo)

         if (cursor.moveToFirst()){

             do {
                 correoUsu  = cursor.getString(0).toString()
                 passUsu = cursor.getString(1).toString()

             }while (cursor.moveToNext())


         }else{
             Toast.makeText(this,"NO HAY DOTOS ERROR SENTENCIA",Toast.LENGTH_SHORT).show()
         }


    }

    private fun registroUsuario() {

        val intent = Intent(this,FormularioRegisterActivity::class.java)
        startActivity(intent)

    }
}