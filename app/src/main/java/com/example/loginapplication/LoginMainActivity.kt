package com.example.loginapplication

import DataBaseUsers.DataBaseHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
            inicioSesion()
        }

        botonSingUp.setOnClickListener {
            registroUsuario()
        }


    }

    private fun validarFormularioLogin () : Boolean{

        val email = findViewById<EditText>(R.id.et_Email)
        val pass = findViewById<EditText>(R.id.et_Password)
        val obteneremail = email.text.toString()
        val obtenerPass = pass.text.toString()

        if(obteneremail.isEmpty() && obtenerPass.isEmpty()){

            Toast.makeText(this,"Ingresar Correo y contraseña",Toast.LENGTH_SHORT).show()
            email.error = "Ingresar Correo Electronico"
            email.requestFocus()
            pass.requestFocus()
            return false

        }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(obteneremail).matches()){
            email.requestFocus()
            Toast.makeText(this,"Correo Electronico Invalido",Toast.LENGTH_SHORT).show()
            return false

        }
        return true
    }


    private fun inicioSesion() {

         if(validarFormularioLogin()) {

             val correo = findViewById<EditText>(R.id.et_Email)
             val contrasena = findViewById<EditText>(R.id.et_Password)

             val obtenerCorreo = correo.text.toString()
             val obtenerContrasena = contrasena.text.toString()

             val cursor = registroDBHelper.loguinData(obtenerCorreo)

             var correoUsu : String = ""
             var passUsu : String = ""

             if (cursor?.moveToFirst()!!) {

                do{
                     correoUsu = cursor.getString(0).toString()
                     passUsu = cursor.getString(1).toString()

                 } while (cursor.moveToNext())

                 if(correoUsu.equals(obtenerCorreo) && passUsu.equals(obtenerContrasena)){

                     Toast.makeText(this, "DOTOS CORRECTOS" + correoUsu , Toast.LENGTH_SHORT).show()

                     val intent = Intent(this,HomeActivity::class.java)
                     startActivity(intent)

                     correo.text.clear()
                     contrasena.text.clear()

                 } else {
                     Toast.makeText(this, "DATOS INCORRECTOS", Toast.LENGTH_SHORT).show()
                 }

             }else{
                 Toast.makeText(this, " NO HAY DOTOS " , Toast.LENGTH_SHORT).show()
                 Log.i("ERROR","No hay datos")
             }

         }
    }

    private fun registroUsuario() {

        val intent = Intent(this,FormularioRegisterActivity::class.java)
        startActivity(intent)

    }
}