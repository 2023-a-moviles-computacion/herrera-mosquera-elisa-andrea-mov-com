package com.example.examen01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Database
import androidx.room.Room
import com.example.examen01.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), AdaptadorListener {

    lateinit var binding: ActivityMainBinding

    var listaPlanetas: MutableList<Planeta> = mutableListOf()

    lateinit var adatador: AdaptadorPlanetas

    lateinit var room: DBPrueba

    lateinit var planeta: Planeta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPlanetas.layoutManager = LinearLayoutManager(this)

        room = Room.databaseBuilder(this, DBPrueba::class.java, "dbPruebas").build()

        obtenerPlanetas(room)

        binding.btnAddUpdate.setOnClickListener {
            if(binding.etPlaneta.text.isNullOrEmpty() || binding.etId.text.isNullOrEmpty() || binding.etNumeroLunas.text.isNullOrEmpty()|| binding.etDiametro.text.isNullOrEmpty()|| binding.etMasa.text.isNullOrEmpty() ) {
                Toast.makeText(this, "DEBES LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.btnAddUpdate.text.equals("agregar")) {
                planeta = Planeta(
                    binding.etPlaneta.text.toString().trim(),
                    binding.etId.text.toString().toInt(),
                    binding.etNumeroLunas.text.toString().toInt(),
                    binding.etDiametro.text.toString().toDouble(),
                    binding.etMasa.text.toString().toDouble(),

                )

                agregarPlaneta(room, planeta)
            } else if(binding.btnAddUpdate.text.equals("actualizar")) {
                planeta.id = binding.etId.text.toString().toInt()

                actualizarPlaneta(room, planeta)
            }
        }

    }

    fun obtenerPlanetas(room: DBPrueba) {
        lifecycleScope.launch {
            listaPlanetas = room.daoPlaneta().obtenerPlanetas()
            adatador = AdaptadorPlanetas(listaPlanetas, this@MainActivity)
            binding.rvPlanetas.adapter = adatador
        }
    }

    fun agregarPlaneta(room: DBPrueba, planeta: Planeta) {
       lifecycleScope.launch {
            room.daoPlaneta().agregarPlaneta(planeta)
            obtenerPlanetas(room)
            limpiarCampos()
        }
    }

    fun actualizarPlaneta(room: DBPrueba, planeta: Planeta) {
        lifecycleScope.launch {
            room.daoPlaneta().actualizarPlaneta(planeta.planeta, planeta.id)
            obtenerPlanetas(room)
            limpiarCampos()
        }
    }



    fun limpiarCampos() {
        planeta.planeta = ""
        planeta.id = 0
        planeta.numeroLunas = 0
        planeta.diametro = 0.0
        planeta.masa = 0.0
        binding.etPlaneta.setText("")
        binding.etId.setText("")
        binding.etNumeroLunas.setText("")
        binding.etDiametro.setText("")
        binding.etMasa.setText("")

        if (binding.btnAddUpdate.text.equals("actualizar")) {
            binding.btnAddUpdate.setText("agregar")
            binding.etPlaneta.isEnabled = true
        }

    }

    override fun onEditItemClick(planeta: Planeta) {
        binding.btnAddUpdate.setText("actualizar")
        binding.etPlaneta.isEnabled = false
        this.planeta = planeta
        binding.etPlaneta.setText(this.planeta.planeta)
        binding.etId.setText(this.planeta.id)
    }

    override fun onDeleteItemClick(planeta: Planeta) {
        lifecycleScope.launch {
            room.daoPlaneta().borrarPlaneta(planeta.planeta)
            adatador.notifyDataSetChanged()
            obtenerPlanetas(room)
        }
    }
}
