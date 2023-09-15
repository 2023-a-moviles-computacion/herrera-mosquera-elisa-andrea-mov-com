package com.example.recetario

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.recetario.util.PreferenceHelper
import com.example.recetario.util.PreferenceHelper.set

class MenuActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogout.setOnClickListener {
            clearSessionPreferece()
            login()

        }

        val btn_crar_receta = findViewById<Button>(R.id.btn_crear_receta)
        btn_crar_receta.setOnClickListener {
            crearReceta()
        }



    }
    private fun crearReceta(){
        val i = Intent(this, CrearRecetaActivity2::class.java)
        startActivity(i)
    }
    private  fun login(){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun clearSessionPreferece(){
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["session"] = false
    }




}