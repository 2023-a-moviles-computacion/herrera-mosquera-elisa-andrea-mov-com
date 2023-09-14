package com.example.examen01

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.google.firebase.FirebaseApp
import com.example.examen01.databinding.ActivityMain2Binding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


class MainActivity2 : AppCompatActivity(), AdaptadorListener2 {

    private val dataList = mutableListOf<Pair<String, Map<String, Any>>>()
    lateinit var binding: ActivityMain2Binding
    lateinit var adatador: AdaptadorPlanetas


    val db : FirebaseFirestore = FirebaseFirestore.getInstance()
    val planetasCollection = db.collection("planetas")

    var codigoSistemaVine: String? = null // Inicializarlo como null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        codigoSistemaVine = intent.getStringExtra("codigoSistema")

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvPlanetas.layoutManager = LinearLayoutManager(this)

        // Inicializa el adaptador aquí
        adatador = AdaptadorPlanetas(emptyList(), this)
        binding.rvPlanetas.adapter = adatador
        cargarDatosYActualizarRecyclerView()


        binding.btnAddUpdateP.setOnClickListener {
            if(binding.etPlaneta.text.isNotBlank() && binding.etEdadPlaneta.text.isNotBlank() && binding.etDescripcionP.text.isNotBlank()) {

                val dato = hashMapOf(
                    "codigoSistema" to binding.etCodigoSistema.text.toString(),

                    "planeta" to binding.etPlaneta.text.toString(),"edadPlaneta" to binding.etEdadPlaneta.text.toString(),
                    //"codigoSistema" to binding.etCodigoSistema.text.toString(),
                   // "planeta" to binding.etPlaneta.text.toString(),
                    "descripcionP" to binding.etDescripcionP.text.toString()
                )
                db.collection("planetas")
                    .document(binding.etCodigoSistema.text.toString())
                    .set(dato)
                    .addOnSuccessListener { resultado ->
                        binding.btnAddUpdateP.text ="Agregar"
                        limpiarCampos()
                        adatador.notifyDataSetChanged()
                        cargarDatosYActualizarRecyclerView()
                    }
                    .addOnFailureListener{ exception ->
                        binding.btnAddUpdateP.text = "No se pudo"
                    }
            }
        }
    }

    fun limpiarCampos() {

        //planeta.masa =""
        binding.etCodigoSistema.setText("")
        binding.etPlaneta.setText("")
        binding.etEdadPlaneta.setText("")
        binding.etDescripcionP.setText("")
       // binding.etMasa.setText("")


        if (binding.btnAddUpdateP.text.equals("agregar")) {
            binding.btnAddUpdateP.setText("actualizar")
            binding.etCodigoSistema.isEnabled = true

            adatador.notifyDataSetChanged()
        }

    }

    private fun cargarDatosYActualizarRecyclerView() {
        codigoSistemaVine = intent.getStringExtra("codigoSistema")
        planetasCollection
            .whereEqualTo("codigoSistema", codigoSistemaVine)
            .get()
            .addOnSuccessListener { documentos ->
                dataList.clear() // Limpia los datos anteriores
                for (documento in documentos) {
                    dataList.add(Pair(documento.id, documento.data))
                }
                val adapter = AdaptadorPlanetas(dataList, this)
                binding.rvPlanetas.adapter = adapter
                adatador.notifyDataSetChanged() // Notifica al adaptador que los datos han cambiado
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "No se ha podido conectar: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onEditItemClick(codigoSistema: String, data: Map<String, Any>) {
        binding.btnAddUpdateP.setText("actualizar")
        binding.etCodigoSistema.isEnabled = false
        binding.etCodigoSistema.setText(codigoSistema)
        binding.etPlaneta.setText(data["planeta"] as? String ?: "")
        binding.etEdadPlaneta.setText(data["edadPlaneta"] as? String ?: "")
        binding.etDescripcionP.setText(data["descripcionP"] as? String ?: "")
        adatador.notifyDataSetChanged()
        cargarDatosYActualizarRecyclerView()

    }

    override fun onDeleteItemClick(codigoSistema: String, data: Map<String, Any>) {
        db.collection("planetas")
            .document(codigoSistema)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(this@MainActivity2, "Elemento eliminado", Toast.LENGTH_SHORT).show()
                adatador.notifyDataSetChanged()
                cargarDatosYActualizarRecyclerView()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this@MainActivity2, "Error al eliminar: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }
}