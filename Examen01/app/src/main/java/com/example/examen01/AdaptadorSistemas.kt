package com.example.examen01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorSistemas(
    val listaSistemas: MutableList<Sistema>,
    val listener: AdaptadorListener
): RecyclerView.Adapter<AdaptadorSistemas.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_sistema, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sistema = listaSistemas[position]

        holder.tvSistema.text = sistema.sistema
        holder.tvEdad.text = sistema.edad
        holder.tvDescripcion.text =sistema.descripcion

        holder.cvSistema.setOnClickListener {
            listener.onEditItemClick(sistema)
        }

        holder.btnBorrar.setOnClickListener {
            listener.onDeleteItemClick(sistema)
        }
    }

    override fun getItemCount(): Int {
        return listaSistemas.size
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        val cvSistema = itemView.findViewById<CardView>(R.id.cvSistema)
        val tvSistema = itemView.findViewById<TextView>(R.id.tvSistema)
        val tvEdad = itemView.findViewById<TextView>(R.id.tvEdad)
        val tvDescripcion = itemView.findViewById<TextView>(R.id.tvDescripcion)
        val btnBorrar = itemView.findViewById<Button>(R.id.btnBorrar)
    }

}