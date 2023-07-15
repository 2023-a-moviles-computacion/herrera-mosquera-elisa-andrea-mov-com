package com.example.examen01

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.examen01.databinding.ActivityMain2Binding
import kotlinx.coroutines.launch


class MainActivity2 : AppCompatActivity(), AdaptadorListener2 {

    lateinit var binding: ActivityMain2Binding
    var listaPlanetas: MutableList<Planeta> = mutableListOf()
    lateinit var adatador: AdaptadorPlanetas
    lateinit var room: DBPrueba
    lateinit var room2: DBPlanetas
    lateinit var planeta: Planeta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvPlanetas.layoutManager = LinearLayoutManager(this)
        room = Room.databaseBuilder(this, DBPrueba:: class.java, "dbPrueba").build()
        room2 = Room.databaseBuilder(this, DBPlanetas::class.java, "dbPlanetas").build()


        val codigoSistema = intent.getIntExtra("codigoSistema" ,0)
        obtenerPlanetas(room2, codigoSistema)

        binding.btnAddUpdateP.setOnClickListener {
            if(binding.etPlaneta.text.isNullOrEmpty() || binding.etEdadPlaneta.text.isNullOrEmpty()) {
                Toast.makeText(this, "DEBES LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.btnAddUpdateP.text.equals("agregar")) {
                planeta = Planeta(
                    binding.etPlaneta.text.toString().trim(),
                    binding.etEdadPlaneta.text.toString().trim(),
                    //  binding.etGalaxia.text.toString().trim(),
                    //binding.etDescripcionPlaneta.text.toString().trim(),
                    codigoSistema

                )

                agregarPlaneta(room2, planeta, codigoSistema)
            } else if(binding.btnAddUpdateP.text.equals("actualizar")) {
                planeta.edadPlaneta = binding.etEdadPlaneta.text.toString().trim()
                // sistema.galaxia =binding.etGalaxia.text.toString().trim()
               // planeta.descripcionPlaneta =binding.etDescripcionPlaneta.text.toString().trim()
                planeta.codigoSistema = codigoSistema
                actualizarPlaneta(room2, planeta, codigoSistema)
            }
        }

    }

    fun obtenerPlanetas(room: DBPlanetas, codigoSistema: Int) {
        lifecycleScope.launch {
            listaPlanetas = room.daoPlaneta().obtenerPlanetaPorCodigoPlaneta(codigoSistema)
            adatador = AdaptadorPlanetas(listaPlanetas, this@MainActivity2)
            binding.rvPlanetas.adapter = adatador
        }
    }

    fun agregarPlaneta(room: DBPlanetas, planeta: Planeta, codigoSistema: Int) {
        lifecycleScope.launch {
            room.daoPlaneta().agregarPlaneta(planeta)
            obtenerPlanetas(room, codigoSistema)
            limpiarCampos()
        }
    }

    fun actualizarPlaneta(room: DBPlanetas, planeta: Planeta, codigoSistema: Int) {
        lifecycleScope.launch {
            room.daoPlaneta().actualizarPlaneta(planeta.planeta, planeta.edadPlaneta, codigoSistema)
            obtenerPlanetas(room, codigoSistema)
            limpiarCampos()
        }
    }

    fun limpiarCampos() {
        planeta.planeta = ""
        planeta.edadPlaneta = ""
        //  sistema.galaxia =""
       // planeta.descripcionPlaneta=""
        binding.etPlaneta.setText("")
        binding.etEdadPlaneta.setText("")
        // binding.etGalaxia.setText("")
        // binding.etDescripcionPlaneta.setText("")

        if (binding.btnAddUpdateP.text.equals("actualizar")) {
            binding.btnAddUpdateP.setText("agregar")
            binding.etPlaneta.isEnabled = true
        }

    }

    override fun onEditItemClick(planeta: Planeta, codigoSistema: Int) {
        binding.btnAddUpdateP.setText("actualizar")
        binding.etPlaneta.isEnabled = false
        this.planeta = planeta
        binding.etPlaneta.setText(this.planeta.planeta)
        binding.etEdadPlaneta.setText(this.planeta.edadPlaneta)
       // binding.etDescripcionPlaneta.setText(this.planeta.descripcionPlaneta)
    }

    override fun onDeleteItemClick(planeta: Planeta, codigoSistema: Int) {
        lifecycleScope.launch {
            room2.daoPlaneta().borrarPlaneta(planeta.planeta)
            adatador.notifyDataSetChanged()
            obtenerPlanetas(room2, codigoSistema)
        }
    }
}
