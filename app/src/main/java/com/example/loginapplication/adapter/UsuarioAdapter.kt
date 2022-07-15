package com.example.loginapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapplication.Objects.Usuario
import com.example.loginapplication.R

class UsuarioAdapter(private val usuarioList: List<Usuario>) : RecyclerView.Adapter<UsuarioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UsuarioViewHolder(layoutInflater.inflate(R.layout.item_usuarios, parent,false))
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val item = usuarioList[position]
        holder.render(item)

    }

    override fun getItemCount(): Int = usuarioList.size
}