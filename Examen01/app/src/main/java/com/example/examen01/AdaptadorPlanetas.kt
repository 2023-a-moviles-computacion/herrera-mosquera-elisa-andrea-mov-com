package com.example.examen01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPlanetas(
    val listaPlanetas:List<Pair<String, Map<String, Any>>>,
    val listener: AdaptadorListener2
): RecyclerView.Adapter<AdaptadorPlanetas.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_planeta, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (id, data) = listaPlanetas[position]

        holder.tvCodigoSistema.text = id
        holder.tvPlaneta.text = data["planeta"] as? String ?: ""
        holder.tvEdadPlaneta.text = data["edadPlaneta"] as? String ?: ""
        holder.tvDescripcionP.text =data["descripcionP"] as? String ?: ""
       // holder.tvMasa.text = data["masa"] as? String ?: ""
        //holder.tvCodigoSistemaPlaneta.text = planeta.codigoSistema.toString()

        holder.cvPlaneta.setOnClickListener {
            listener.onEditItemClick(id, data)
        }

        holder.btnBorrarP.setOnClickListener {
            val (id, data) = listaPlanetas[position]
            listener.onDeleteItemClick(id, data)
        }
    }

    override fun getItemCount(): Int {
        return listaPlanetas.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cvPlaneta = itemView.findViewById<CardView>(R.id.cvPlaneta)
        val tvCodigoSistema = itemView.findViewById<TextView>(R.id.tvCodigoSistema)
        val tvPlaneta = itemView.findViewById<TextView>(R.id.tvPlaneta)
        val tvEdadPlaneta = itemView.findViewById<TextView>(R.id.tvEdadPlaneta)
        val tvDescripcionP = itemView.findViewById<TextView>(R.id.tvDescripcionP)
       // val tvMasa = itemView.findViewById<TextView>(R.id.tvMasa)
        val btnBorrarP = itemView.findViewById<Button>(R.id.btnBorrarP)
    }
}
