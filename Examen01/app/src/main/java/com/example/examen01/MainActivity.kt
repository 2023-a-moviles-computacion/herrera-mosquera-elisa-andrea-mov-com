package com.example.examen01


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examen01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SistemaSolarAdapter.OnItemClickListener {

    var dataSet: MutableList<SistemaSolar> = arrayListOf()
    lateinit var mAdapter: SistemaSolarAdapter

    lateinit var binding: ActivityMainBinding

    var isEditar = false
    var posicion = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAdapter = SistemaSolarAdapter(this)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = mAdapter

        binding.btnAddEdit.setOnClickListener {
            if (!isEditar) {
                val nombre = binding.etNombre.text.toString().trim()
                val edad = binding.etEdad.text.toString().toInt()
                val galaxia = binding.etGalaxia.text.toString().trim()
                val distancia = binding.etDistancia.text.toString().toDouble()
                val descripcion = binding.etDescripcion.text.toString().trim()

                val sistemaSolar = SistemaSolar(nombre, edad, galaxia, distancia, descripcion)
                dataSet.add(sistemaSolar)

                mAdapter.submitList(dataSet)
                mAdapter.notifyDataSetChanged()

                binding.etNombre.setText("")
                binding.etEdad.setText("")
                binding.etGalaxia.setText("")
                binding.etDistancia.setText("")
                binding.etDescripcion.setText("")
            } else {
                val nombre = binding.etNombre.text.toString()
                val edad = binding.etEdad.text.toString().toInt()
                val galaxia = binding.etGalaxia.text.toString()
                val distancia = binding.etDistancia.text.toString().toDouble()
                val descripcion = binding.etDescripcion.text.toString()

                dataSet[posicion].nombre = nombre
                dataSet[posicion].edad = edad
                dataSet[posicion].galaxia = galaxia
                dataSet[posicion].distancia = distancia
                dataSet[posicion].descripcion = descripcion

                posicion = -1
                isEditar = false

                mAdapter.submitList(dataSet)
                mAdapter.notifyDataSetChanged()

                binding.etNombre.setText("")
                binding.etEdad.setText("")
                binding.etGalaxia.setText("")
                binding.etDistancia.setText("")
                binding.etDescripcion.setText("")
            }
        }
    }


    override fun onItemEditar(position: Int, item: SistemaSolar) {
        isEditar = true
        posicion = position

        binding.etNombre.setText(item.nombre)
        binding.etEdad.setText(item.edad.toString())
        binding.etGalaxia.setText(item.galaxia)
        binding.etDistancia.setText(item.distancia.toString())
        binding.etDescripcion.setText(item.descripcion)
    }

    override fun onItemBorrar(position: Int) {
        dataSet.removeAt(position)
        mAdapter.submitList(dataSet)
        mAdapter.notifyDataSetChanged()
    }
}


