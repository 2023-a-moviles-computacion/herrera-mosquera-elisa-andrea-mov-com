package com.example.examen01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examen01.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity(), AdaptadorListener {

    private val dataList = mutableListOf<Pair<String, Map<String, Any>>>()
    lateinit var binding: ActivityMainBinding
    lateinit var adatador: AdaptadorSistemas

    val db : FirebaseFirestore = FirebaseFirestore.getInstance()
    val sistemasCollection = db.collection("sistemas")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvSistemas.layoutManager = LinearLayoutManager(this)


        adatador = AdaptadorSistemas(emptyList(),this)
        binding.rvSistemas.adapter = adatador
        cargarDatosYActualizarRecyclerView()


        binding.btnAddUpdate.setOnClickListener {
            if(binding.etSistema.text.isNotBlank() && binding.etEdad.text.isNotBlank() && binding.etGalaxia.text.isNotBlank()&& binding.etDescripcion.text.isNotBlank()) {
               val dato = hashMapOf(

                   "codigoSistema" to binding.etCodigoSistema.text.toString(),
                   "Sistema" to binding.etSistema.text.toString(),
                   "edad" to binding.etEdad.text.toString(),
                   "galaxia" to binding.etGalaxia.text.toString(),
                   "descripcion" to binding.etDescripcion.text.toString()
               )

                db.collection("sistemas")
                    .document(binding.etCodigoSistema.text.toString())
                    .set(dato)
                    .addOnSuccessListener { resultado ->
                        binding.btnAddUpdate.text ="Agregar"
                        limpiarCampos()
                        adatador.notifyDataSetChanged()
                        cargarDatosYActualizarRecyclerView()
                    }
                    .addOnFailureListener{ exception ->
                        binding.btnAddUpdate.text = "No se agrego"
                    }
            }
        }
    }


    fun limpiarCampos() {
        binding.etCodigoSistema.setText("")
        binding.etSistema.setText("")
        binding.etEdad.setText("")
        binding.etGalaxia.setText("")
        binding.etDescripcion.setText("")
        if (binding.btnAddUpdate.text.equals("actualizar")) {
            binding.btnAddUpdate.setText("agregar")
            binding.etCodigoSistema.isEnabled = true

            adatador.notifyDataSetChanged()
            cargarDatosYActualizarRecyclerView()
        }

    }

    private fun cargarDatosYActualizarRecyclerView() {
        sistemasCollection
            .get()
            .addOnSuccessListener { documentos ->
                dataList.clear()
                for (documento in documentos) {
                    dataList.add(Pair(documento.id, documento.data))
                }
                val adapter = AdaptadorSistemas(dataList, this)
                binding.rvSistemas.adapter = adapter
                adatador.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    this,
                    "No se ha podido conectar: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    override fun onEditItemClick(codigoSistema: String, data: Map<String, Any>) {
        binding.btnAddUpdate.setText("Actualizar")
        binding.etCodigoSistema.isEnabled = false
        binding.etCodigoSistema.setText(codigoSistema)
        binding.etSistema.setText(data["sistema"] as? String ?: "")
        binding.etEdad.setText(data["edad"] as? String ?: "")
        binding.etGalaxia.setText(data["galaxia"] as? String ?: "")
        binding.etDescripcion.setText(data["descripcion"] as? String ?: "")
        adatador.notifyDataSetChanged()
        cargarDatosYActualizarRecyclerView()
    }

    override fun onDeleteItemClick(codigoSistema: String, data: Map<String, Any>) {
        db.collection("sistemas")
            .document(codigoSistema)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(this@MainActivity, "Elemento eliminado", Toast.LENGTH_SHORT).show()
                adatador.notifyDataSetChanged()
                cargarDatosYActualizarRecyclerView()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this@MainActivity, "Error al eliminar: ${exception.message}", Toast.LENGTH_SHORT).show()
            }

    }

}