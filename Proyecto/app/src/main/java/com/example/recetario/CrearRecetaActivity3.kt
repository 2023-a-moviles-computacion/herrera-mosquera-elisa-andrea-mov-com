package com.example.recetario

import RecetaDao
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class CrearRecetaActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_receta3)
        val btnPublicar = findViewById<Button>(R.id.btn_publicar)
        btnPublicar.setOnClickListener {
            // Abrir la nueva actividad
            val intent = Intent(this, CrearRecetaActivity3::class.java)
            startActivity(intent)

            Toast.makeText(this, "Receta publicada exitosamente", Toast.LENGTH_SHORT).show()
        }
    }

}