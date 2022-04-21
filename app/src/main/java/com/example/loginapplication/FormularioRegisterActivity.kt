package com.example.loginapplication

import DataBaseUsers.DataBaseHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FormularioRegisterActivity : AppCompatActivity() {

    private lateinit var registroDBHelper : DataBaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_register)

        val botonRegistro = findViewById<Button>(R.id.btn_aceptar)
        val botonCancelarRegistro = findViewById<Button>(R.id.btn_cancelar)

        registroDBHelper = DataBaseHelper(this)


        botonRegistro.setOnClickListener(){
            validarfornmulario()
        }

        botonCancelarRegistro.setOnClickListener(){
            startActivity(Intent(this,LoginMainActivity::class.java))
        }


    }


    private fun validarfornmulario (){

         if(validarEmailRegistro() && validarPasswordRegistro() && validarNombreApellido() && validarTelefono()){

             Toast.makeText(this,"Formulario Valido",Toast.LENGTH_SHORT).show()

             val email = findViewById<EditText>(R.id.et_email_form)
             val pass = findViewById<EditText>(R.id.et_passwor_form)
             val name = findViewById<EditText>(R.id.et_name)
             val lastname = findViewById<EditText>(R.id.et_lastname)
             val tel = findViewById<EditText>(R.id.et_Number)

             val traerEmail = email.text.toString()
             val traerPass = pass.text.toString()
             val traername = name.text.toString()
             val traerlastname = lastname.text.toString()
             val traertell = tel.text.toString()

             registroDBHelper.AddUser(traername,traerlastname,traerEmail,traerPass,traertell)

             Toast.makeText(this,"Usuario guardado",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"No es valido y datos no guardados",Toast.LENGTH_SHORT).show()
        }
    }



   private fun validarEmailRegistro () : Boolean{

        val email = findViewById<EditText>(R.id.et_email_form)
        val obteneremail = email.getText().toString()

        if(obteneremail.isEmpty() && obteneremail == null){

            Toast.makeText(this,"Ingresar Correo Electronico",Toast.LENGTH_SHORT).show()
            email.setError("Ingresar Correo Electronico")
            email.requestFocus()
            return false

        }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(obteneremail).matches()){
            email.requestFocus()
            Toast.makeText(this,"Correo Electronico Invalido",Toast.LENGTH_SHORT).show()
            return false

        }
       return true
    }

    private fun validarPasswordRegistro() : Boolean{

        val passwordForm = findViewById<EditText>(R.id.et_passwor_form)
        val ObtenerPassword = passwordForm.getText().toString()

        if(ObtenerPassword.isEmpty() && ObtenerPassword == null){
            Toast.makeText(this,"Ingresar Password",Toast.LENGTH_SHORT).show()
            return false
        }else if(ObtenerPassword.length <= 6){
            Toast.makeText(this,"Password Devil",Toast.LENGTH_SHORT).show()
            return false
        }
       return true
    }

   private fun validarTelefono() : Boolean{

        val phone = findViewById<EditText>(R.id.et_Number)
        val obtenerPhone = phone.getText().toString()

        if(obtenerPhone.isNotEmpty() && obtenerPhone !=null){
            return true
        }
        Toast.makeText(this," Ingresar telefono",Toast.LENGTH_SHORT).show()
        phone.requestFocus()
        return false

    }

    private fun validarNombreApellido() : Boolean {
        val nombre = findViewById<EditText>(R.id.et_name)
        val apellido = findViewById<EditText>(R.id.et_lastname)

        val ObtenerNombre = nombre.getText().toString()
        val ObtenerApellido = apellido.getText().toString()

        if(ObtenerNombre.isNotBlank() && ObtenerNombre != null && ObtenerApellido.isNotEmpty() && ObtenerApellido != null){
            return true
        }
        Toast.makeText(this," Ingresar Nombre o apellido",Toast.LENGTH_SHORT).show()
        nombre.requestFocus()
        return false
    }

}