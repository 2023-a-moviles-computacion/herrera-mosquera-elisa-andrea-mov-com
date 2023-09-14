package com.example.examen01

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorSistemas(
    val listaSistemas: List<Pair<String, Map<String, Any>>>,
    val listener: AdaptadorListener
): RecyclerView.Adapter<AdaptadorSistemas.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_sistema, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (id, data) = listaSistemas[position]
        holder.tvCodigoSistema.text = id
        holder.tvSistema.text = data["sistema"] as? String ?: ""
        holder.tvEdad.text = data["edad"] as? String ?: ""
        holder.tvGalaxia.text =data["galaxia"] as? String ?: ""
        holder.tvDescripcion.text =data["descripcion"] as? String ?: ""
        holder.cvSistema.setOnClickListener {
            listener.onEditItemClick(id, data)
        }

        holder.btnBorrar.setOnClickListener {
            val (id,data)=listaSistemas[position]
            listener.onDeleteItemClick(id,data)
        }
        holder.btnCrear.setOnClickListener {
            val(id, _) = listaSistemas[position]
            val intent = Intent(holder.itemView.context, MainActivity2::class.java)
            intent.putExtra("codigoSistema", id)
            holder.itemView.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return listaSistemas.size
    }

    
    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {

        val cvSistema = itemView.findViewById<CardView>(R.id.cvSistema)
        val tvCodigoSistema = itemView.findViewById<TextView>(R.id.tvCodigoSistema)
        val tvSistema = itemView.findViewById<TextView>(R.id.tvSistema)
        val tvEdad = itemView.findViewById<TextView>(R.id.tvEdad)
        val tvGalaxia = itemView.findViewById<TextView>(R.id.tvGalaxia)
       val tvDescripcion = itemView.findViewById<TextView>(R.id.tvDescripcion)
        val btnBorrar = itemView.findViewById<Button>(R.id.btnBorrar)
        val btnCrear = itemView.findViewById<Button>(R.id.btnCrear)
    }

}