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

    private fun validarEmail () : Boolean{

        val email = findViewById<EditText>(R.id.et_Email)
        val obteneremail = email.text.toString()

        if(obteneremail.isEmpty() && obteneremail == null){

            Toast.makeText(this,"Ingresar Correo Electronico",Toast.LENGTH_SHORT).show()
            email.error = "Ingresar Correo Electronico"
            email.requestFocus()
            return false

        }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(obteneremail).matches()){
            email.requestFocus()
            Toast.makeText(this,"Correo Electronico Invalido",Toast.LENGTH_SHORT).show()
            return false

        }
        return true
    }


     fun validarLogin() {

         val correo = findViewById<EditText>(R.id.et_Email)
         val contrasena = findViewById<EditText>(R.id.et_Password)

         val obtenerCorreo = correo.text.toString()
         val obtenerContrasena = contrasena.text.toString()

         val cursor = registroDBHelper.loguinData(obtenerCorreo)

         if(validarEmail()) {
             if (cursor?.moveToFirst() == true) {

                 do {
                     var  correoUsu = cursor.getString(0).toString()
                     var  passUsu = cursor.getString(1).toString()
                 } while (cursor.moveToNext())

                 Toast.makeText(this, "HAY DOTOS ", Toast.LENGTH_SHORT).show()

             } else {
                 Toast.makeText(this, "NO HAY DOTOS ERROR SENTENCIA", Toast.LENGTH_SHORT).show()
             }
         }

    }

    private fun registroUsuario() {

        val intent = Intent(this,FormularioRegisterActivity::class.java)
        startActivity(intent)

    }
}