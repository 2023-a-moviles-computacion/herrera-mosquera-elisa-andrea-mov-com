package com.example.recetario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.recetario.util.PreferenceHelper
import com.example.recetario.util.PreferenceHelper.get
import com.example.recetario.util.PreferenceHelper.set

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferences = PreferenceHelper.defaultPrefs(this)
        if(preferences["session", false])
            menu()
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
        createSessionPreference()
        startActivity(i)
        finish()

    }
    private fun createSessionPreference(){
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["session"] = true
    }
}