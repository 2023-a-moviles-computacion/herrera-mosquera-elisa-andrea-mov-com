package com.example.examen01


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examen01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val botonSistemaSolares = findViewById<Button>(R.id.btnSistemaSolar)
        botonSistemaSolares
            .setOnClickListener {
                irActividad(SistemaSolares::class.java)
            }
        val botonRecetas = findViewById<Button>(R.id.btnPlanetas)
        botonRecetas
            .setOnClickListener {
                irActividad(Planetas::class.java)
            }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent= Intent(this,clase)
        startActivity(intent)
    }
}