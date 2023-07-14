package com.example.crud_room_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.crud_room_kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), AdaptadorListener {

    lateinit var binding: ActivityMainBinding

    var listaSistemas: MutableList<Sistema> = mutableListOf()

    lateinit var adatador: AdaptadorSistemas

    lateinit var room: DBPrueba

    lateinit var sistema: Sistema

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvSistemas.layoutManager = LinearLayoutManager(this)

        room = Room.databaseBuilder(this, DBPrueba::class.java, "dbPruebas").build()

        obtenerSistemas(room)

        binding.btnAddUpdate.setOnClickListener {
            if(binding.etSistemaSolar.text.isNullOrEmpty() || binding.etEdad.text.isNullOrEmpty() || binding.etDescripcion.text.isNullOrEmpty()) {
                Toast.makeText(this, "DEBES LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.btnAddUpdate.text.equals("agregar")) {
                sistema = Sistema(
                    binding.etSistemaSolar.text.toString().trim(),
                    binding.etEdad.text.toString().trim(),
                    binding.etDescripcion.text.toString().trim()
                )

                agregarSistema(room, sistema)
            } else if(binding.btnAddUpdate.text.equals("actualizar")) {
                sistema.edad = binding.etEdad.text.toString().trim()
                sistema.descripcion =binding.etDescripcion.text.toString().trim()

                actualizarSistema(room, sistema)
            }
        }

    }

    fun obtenerSistemas(room: DBPrueba) {
        lifecycleScope.launch {
            listaSistemas = room.daoSistema().obtenerSistemas()
            adatador = AdaptadorSistemas(listaSistemas, this@MainActivity)
            binding.rvSistemas.adapter = adatador
        }
    }

    fun agregarSistema(room: DBPrueba, sistema: Sistema) {
        lifecycleScope.launch {
            room.daoSistema().agregarSistema(sistema)
            obtenerSistemas(room)
            limpiarCampos()
        }
    }

    fun actualizarSistema(room: DBPrueba, sistema: Sistema) {
        lifecycleScope.launch {
            room.daoSistema().actualizarSistema(sistema.sistema, sistema.edad, sistema.descripcion)
            obtenerSistemas(room)
            limpiarCampos()
        }
    }

    fun limpiarCampos() {
        sistema.sistema = ""
        sistema.edad = ""
        sistema.descripcion=""
        binding.etSistemaSolar.setText("")
        binding.etEdad.setText("")
        binding.etDescripcion.setText("")

        if (binding.btnAddUpdate.text.equals("actualizar")) {
            binding.btnAddUpdate.setText("agregar")
            binding.etSistemaSolar.isEnabled = true
        }

    }

    override fun onEditItemClick(sistema: Sistema) {
        binding.btnAddUpdate.setText("actualizar")
        binding.etSistemaSolar.isEnabled = false
        this.sistema = sistema
        binding.etSistemaSolar.setText(this.sistema.sistema)
        binding.etEdad.setText(this.sistema.edad)
        binding.etDescripcion.setText(this.sistema.descripcion)
    }

    override fun onDeleteItemClick(sistema: Sistema) {
        lifecycleScope.launch {
            room.daoSistema().borrarSistema(sistema.sistema)
            adatador.notifyDataSetChanged()
            obtenerSistemas(room)
        }
    }
}