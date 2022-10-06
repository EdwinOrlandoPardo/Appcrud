package com.example.loginapplication.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapplication.Objects.Usuario
import com.example.loginapplication.R

class UsuarioViewHolder(view: View): RecyclerView.ViewHolder(view)  {

    val nombre = view.findViewById<TextView>(R.id.usu_nombre)
    val apellido = view.findViewById<TextView>(R.id.usu_apellido)
    val email = view.findViewById<TextView>(R.id.usu_email)
    val telefono = view.findViewById<TextView>(R.id.usu_telefono)




    fun render(usuario: Usuario, onclickListener:(Usuario) -> Unit){

        nombre.text = usuario.userNombre
        apellido.text = usuario.userApellido
        email.text = usuario.userEmail
        telefono.text = usuario.userTelefono

        itemView.setOnClickListener{onclickListener(usuario)}


    }


}