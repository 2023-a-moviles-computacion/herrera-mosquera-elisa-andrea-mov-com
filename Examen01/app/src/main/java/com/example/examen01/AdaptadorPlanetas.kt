package com.example.examen01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPlanetas(
    val listaPlanetas: MutableList<Planeta>,
    val listener: AdaptadorListener2
): RecyclerView.Adapter<AdaptadorPlanetas.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_planeta, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val planeta = listaPlanetas[position]

        holder.tvPlaneta.text = planeta.planeta
        holder.tvEdadPlaneta.text = planeta.edadPlaneta.toString()
        holder.tvDescripcionP.text = planeta.descripcionP
       // holder.tvMasa.text = planeta.masa
        holder.tvCodigoSistemaPlaneta.text = planeta.codigoSistema.toString()

        holder.cvPlaneta.setOnClickListener {
            listener.onEditItemClick(planeta, planeta.codigoSistema)
        }

        holder.btnBorrarP.setOnClickListener {
            listener.onDeleteItemClick(planeta, planeta.codigoSistema)
        }
    }

    override fun getItemCount(): Int {
        return listaPlanetas.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cvPlaneta = itemView.findViewById<CardView>(R.id.cvPlaneta)
        val tvCodigoSistemaPlaneta = itemView.findViewById<TextView>(R.id.tvCodigoSistema)
        val tvPlaneta = itemView.findViewById<TextView>(R.id.tvPlaneta)
        val tvEdadPlaneta = itemView.findViewById<TextView>(R.id.tvEdadPlaneta)
        val tvDescripcionP = itemView.findViewById<TextView>(R.id.tvDescripcionP)
       // val tvMasa = itemView.findViewById<TextView>(R.id.tvMasa)
        val btnBorrarP = itemView.findViewById<Button>(R.id.btnBorrarP)
    }
}
