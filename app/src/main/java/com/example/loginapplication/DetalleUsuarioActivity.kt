package com.example.loginapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.loginapplication.DataBaseUsers.DataBaseHelper
import com.example.loginapplication.Objects.Usuario

class DetalleUsuarioActivity : AppCompatActivity() {

    private lateinit var accesoDataBaseHelper : DataBaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_usuario)

        accesoDataBaseHelper = DataBaseHelper(this)

         ID_USUARIO  = intent.getIntExtra("id",0)
       // ID_USUARIO = idExtra?.getInt("id")!!

        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        val btnOption = findViewById<ImageButton>(R.id.btn_opciones)
        val imgPerfil = findViewById<ImageView>(R.id.img_profile)

        btnBack.setOnClickListener{
            startActivity(Intent(this,HomeActivity::class.java))
        }

        btnOption.setOnClickListener{
            OptionMenuDetalleUsuario()
        }



        DetalleUsuarioPorId()



    }

    companion object{
        private var ID_USUARIO = 0
        var id : Int = 0
        var nombre : String = ""
        var apellido : String = ""
        var email : String = ""
        var telefono : String = ""
    }

    /**
     * funcion detalleUsurioPorId
     *
     * obtener los datos de los usuario por medio del id
     *
     * id - variable global obtenida de la anterior activity
     *
     *retorna  los datos del usuario e imprime en la vista detalle usuario
     */
    private fun DetalleUsuarioPorId( ){

        val cursor = accesoDataBaseHelper.getDataPorId(ID_USUARIO)


        var nombreView = findViewById<TextView>(R.id.txv_user_name)
        //var apellidoView = findViewById<TextView>(R.id.usu_apellido)
        var emailView = findViewById<TextView>(R.id.txt_detalle_email)
        var telefonoView = findViewById<TextView>(R.id.txt_detalle_telefono)

        if (cursor?.moveToFirst()!!) {
            do {

                 id = (cursor.getInt(0))
                 nombre = (cursor.getString(1).toString())
                 apellido = (cursor.getString(2).toString())
                 email = (cursor.getString(3).toString())
                 telefono = (cursor.getString(4).toString())


            } while (cursor.moveToNext())

            nombreView.text = nombre.toString() + " " + apellido.toString()
            emailView.text = email.toString()
            telefonoView.text = telefono.toString()


        }else{
            Toast.makeText(this, "No se pudo consultar ERROR", Toast.LENGTH_SHORT).show()
        }
    }


    private fun OptionMenuDetalleUsuario(){

        val option : Int = 0

        when (option){

            1 -> {
                UpdateUsuario()
            }

            2 -> {
                elimiarUsuario()
            }
        }

    }

    private fun elimiarUsuario(){
        if (ID_USUARIO != null){

            //mensaje de confirmacion seguridad
            // = a si eliminar
            // = a no descartar mensaje
            accesoDataBaseHelper.deleteUser(ID_USUARIO)
        }else {

        }


    }

    private fun  UpdateUsuario(){

    }
}