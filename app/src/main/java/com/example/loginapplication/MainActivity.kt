package com.example.loginapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    var emailPureba : String = "edwin@gmail.com"
    var passwordPrueba : String = "123prueba"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val botonLogin = findViewById<Button>(R.id.btn_login)
        val botonSingUp = findViewById<Button>(R.id.btn_singup)



        botonLogin.setOnClickListener {
            validarLogin()
        }

        botonSingUp.setOnClickListener {
            registroUsuario()
        }


    }



    private fun ValidarEmail() : Boolean{

        val email = findViewById<EditText>(R.id.et_Email)
        val obtenerEmail =  email.text.toString()

        return if (obtenerEmail.isEmpty()){
            email.requestFocus()
            email.setError("Ingresar Correo Electronico")
            false
        }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(obtenerEmail).matches()){
            email.requestFocus()
            email.setError("Correo Electronico no valido")
            false
        }else {
            return true
        }
    }

    private  fun ValidarPassWord(){

        val password = findViewById<EditText>(R.id.et_Password)
        val obtenerpassword = password.text.toString()


    }

     fun validarLogin() {


    }

    private fun registroUsuario() {

        val intent = Intent(this,FormularioRegisterActivity::class.java)
        startActivity(intent)

    }
}