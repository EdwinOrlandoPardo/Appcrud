package com.example.loginapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.loginapplication.DataBaseUsers.DataBaseHelper
import com.example.loginapplication.DialogAlert.confimationAlertDialog
import com.example.loginapplication.Objects.Usuario

class DetalleUsuarioActivity : AppCompatActivity(), confimationAlertDialog.listenerAlertdialog {

    private lateinit var accesoDataBaseHelper : DataBaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_usuario)

        accesoDataBaseHelper = DataBaseHelper(this)

        ID_USUARIO  = intent.getIntExtra("id",0)


        val btnOption = findViewById<ImageButton>(R.id.btn_opciones)
        val imgPerfil = findViewById<ImageView>(R.id.img_profile)



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



     fun elimiarUsuario(){

        if (ID_USUARIO != null){

            if(accesoDataBaseHelper.deleteUser(ID_USUARIO) == 0){
                Toast.makeText(this@DetalleUsuarioActivity,
                    "no se pudo eliminar el usuraio" ,Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@DetalleUsuarioActivity,
                    "usuraio eliminado" ,Toast.LENGTH_SHORT).show()
            }

        }else {
            Toast.makeText(this@DetalleUsuarioActivity,
                "Usuraio No existe" ,Toast.LENGTH_SHORT).show()
        }
    }

    private fun  UpdateUsuario(){

    }

    fun showMenu(v: View) {
        PopupMenu(this, v).apply {
            setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
                    item: MenuItem? ->

                when (item?.itemId){
                    R.id.itm_modificar ->{
                        //UpdateUsuario()
                        Toast.makeText(this@DetalleUsuarioActivity,
                            "Actualizar Usuario", Toast.LENGTH_SHORT).show()

                        true
                    }
                    R.id.itm_eliminar -> {

                        Toast.makeText(this@DetalleUsuarioActivity,
                            "Eliminar usuraio",Toast.LENGTH_SHORT).show()

                        showDialogDetalleUsuario()

                        true
                    }
                }
                false

            })
            inflate(R.menu.menuoptions)
            show()
        }
    }


    fun showDialogDetalleUsuario(){
        val dialog = confimationAlertDialog()
        dialog.show(supportFragmentManager,"eliminar")
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        elimiarUsuario()
    }

    override fun ondialogNegativeclick(dialog: DialogFragment) {
        dialog.dismiss()
    }
}