package com.example.examen01

import android.media.Image.Plane
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPlanetas(
    val listaPlanetas: MutableList<Planeta>,
    val listener: AdaptadorListener
): RecyclerView.Adapter<AdaptadorPlanetas.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_planetas, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val planeta = listaPlanetas[position]

        holder.tvPlaneta.text = planeta.planeta
        holder.tvId.text = planeta.id.toString()
        holder.tvNumeroLunas.text = planeta.numeroLunas.toString()
        holder.tvDiametro.text = planeta.diametro.toString()
        holder.tvMasa.text = planeta.masa.toString()

        holder.cvPlaneta.setOnClickListener {
            listener.onEditItemClick(planeta)
        }

        holder.btnBorrar.setOnClickListener {
            listener.onDeleteItemClick(planeta)
        }
    }

    override fun getItemCount(): Int {
        return listaPlanetas.size
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        val cvPlaneta = itemView.findViewById<CardView>(R.id.cvPlaneta)
        val tvPlaneta = itemView.findViewById<TextView>(R.id.tvPlaneta)
        val tvId = itemView.findViewById<TextView>(R.id.tvId)
        val tvNumeroLunas = itemView.findViewById<TextView>(R.id.tvNumeroLunas)
        val tvDiametro = itemView.findViewById<TextView>(R.id.tvDiametro)
        val tvMasa = itemView.findViewById<TextView>(R.id.tvMasa)

        val btnBorrar = itemView.findViewById<Button>(R.id.btnBorrar)
    }

}