package com.example.recetario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvRegistro = findViewById<TextView>(R.id.tv_registro)
        tvRegistro.setOnClickListener{
            registro()

        }

        val btnMenu = findViewById<Button>(R.id.btn_menu)
        btnMenu.setOnClickListener {
            menu()

        }

    }
    private fun registro(){
        val i = Intent(this,RegisterActivity::class.java)
        startActivity(i)

    }
    private fun menu(){
        val i = Intent(this,MenuActivity::class.java)
        startActivity(i)

    }
}