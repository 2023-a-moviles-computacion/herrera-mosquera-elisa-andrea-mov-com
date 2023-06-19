package com.example.aciclovida
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    val botonCicloVida = findViewById<Button>(R.id.btn_Ciclo_Vida)
    botonCicloVida
    .setOnClickListener{
        irActividad(AACicloVida::class.java)
    }
    val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
    botonListView
        .setOnClickListener {
            irActividad(BListView::class.java)
    }
}

    @SuppressLint("SuspiciousIndentation")
    fun irActividad(clase:Class<*>){
        val intent = Intent (this, clase)
        startActivity(intent)
    }
}