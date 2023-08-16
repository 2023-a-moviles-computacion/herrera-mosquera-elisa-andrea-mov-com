package com.example.examen01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.examen01.databinding.ActivityMainBinding
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
            if(binding.etSistema.text.isNullOrEmpty() || binding.etEdad.text.isNullOrEmpty() || binding.etGalaxia.text.isNullOrEmpty()|| binding.etDescripcion.text.isNullOrEmpty()) {
                Toast.makeText(this, "DEBES LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.btnAddUpdate.text.equals("agregar")) {
                sistema = Sistema(
                    binding.etCodigoSistema.text.toString().toInt(),
                    binding.etSistema.text.toString().trim(),
                    binding.etEdad.text.toString().trim(),
                   binding.etGalaxia.text.toString().trim(),
                  binding.etDescripcion.text.toString().trim()

                )

                agregarSistema(room, sistema)
            } else if(binding.btnAddUpdate.text.equals("actualizar")) {
                sistema.edad = binding.etEdad.text.toString().trim()
               sistema.galaxia =binding.etGalaxia.text.toString().trim()
               sistema.descripcion =binding.etDescripcion.text.toString().trim()
                sistema.codigoSistema = binding.etCodigoSistema.text.toString().toInt()
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
            room.daoSistema().actualizarSistema(sistema.codigoSistema, sistema.sistema, sistema.edad, sistema.galaxia,sistema.descripcion)
            obtenerSistemas(room)
            limpiarCampos()
        }
    }

    fun limpiarCampos() {
        sistema.codigoSistema=0
        sistema.sistema = ""
        sistema.edad = ""
        sistema.galaxia =""
        sistema.descripcion=""

        binding.etCodigoSistema.setText("")
        binding.etSistema.setText("")
        binding.etEdad.setText("")
        binding.etGalaxia.setText("")
        binding.etDescripcion.setText("")


        if (binding.btnAddUpdate.text.equals("actualizar")) {
            binding.btnAddUpdate.setText("agregar")
            binding.etCodigoSistema.isEnabled = true
        }

    }

    override fun onEditItemClick(sistema: Sistema) {
        binding.btnAddUpdate.setText("actualizar")
        binding.etCodigoSistema.isEnabled = false
        this.sistema = sistema
        binding.etCodigoSistema.setText(this.sistema.codigoSistema.toString())
        binding.etSistema.setText(this.sistema.sistema)
        binding.etEdad.setText(this.sistema.edad)
        binding.etGalaxia.setText(this.sistema.galaxia)
       binding.etDescripcion.setText(this.sistema.descripcion)

    }
    override fun onDeleteItemClick(sistema: Sistema) {
        lifecycleScope.launch {
            room.daoSistema().borrarSistema(sistema.codigoSistema)
            adatador.notifyDataSetChanged()
            obtenerSistemas(room)
        }
    }
}