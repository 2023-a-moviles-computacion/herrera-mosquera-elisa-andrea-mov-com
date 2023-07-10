package com.example.examen01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class SistemaSolarAdapter(private val itemClickListener: OnItemClickListener): ListAdapter<SistemaSolar, SistemaSolarAdapter.ViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<SistemaSolar>() {
        override fun areItemsTheSame(oldItem: SistemaSolar, newItem: SistemaSolar): Boolean {
            return oldItem.nombre == newItem.nombre
            return oldItem.edad == newItem.edad
            return oldItem.galaxia == newItem.galaxia
            return oldItem.distancia == newItem.distancia
            return oldItem.descripcion == newItem.descripcion


        }

        override fun areContentsTheSame(oldItem: SistemaSolar, newItem: SistemaSolar): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_sistemasolar, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val tvNombre = itemView.findViewById(R.id.tvNombre) as TextView
        private val tvEdad = itemView.findViewById(R.id.tvEdad) as TextView
        private val tvGalaxia = itemView.findViewById(R.id.tvGalaxia) as TextView
        private val tvDistancia = itemView.findViewById(R.id.tvDistancia) as TextView
        private val tvDescripcion = itemView.findViewById(R.id.tvDescripcion) as TextView

        private val btnEditar = itemView.findViewById(R.id.btnEdit) as Button
        private val btnBorrar = itemView.findViewById(R.id.btnDelete) as Button

        fun bind(item: SistemaSolar, clickListener: OnItemClickListener) {
            tvNombre.text = item.nombre
            tvEdad.text = item.edad.toString()
            tvGalaxia.text = item.galaxia
            tvDistancia.text = item.distancia.toString()
            tvDescripcion.text = item.descripcion

            btnEditar.setOnClickListener { clickListener.onItemEditar(adapterPosition, item) }
            btnBorrar.setOnClickListener { clickListener.onItemBorrar(adapterPosition) }
        }
    }

    interface OnItemClickListener {
        fun onItemEditar(position: Int, item: SistemaSolar)
        fun onItemBorrar(position: Int)
    }
}