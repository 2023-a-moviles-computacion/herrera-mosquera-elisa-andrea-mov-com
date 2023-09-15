package com.example.recetario

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast


class CrearRecetaActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_receta2)

        val btnSiguiente = findViewById<Button>(R.id.btn_siguiente)
        btnSiguiente.setOnClickListener {
            // Abrir la nueva actividad
            val intent = Intent(this, CrearRecetaActivity3::class.java)
            startActivity(intent)


    }

}
}