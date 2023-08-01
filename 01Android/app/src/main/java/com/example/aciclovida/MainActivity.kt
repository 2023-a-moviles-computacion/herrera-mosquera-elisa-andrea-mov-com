package com.example.aciclovida
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Base de datos sqlite
        EBaseDeDatos.tablaEntrenador =ESqliteHelperEntrenador(this)


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
        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito
            .setOnClickListener {
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                //val callbackIntentPickUri
                //callbackIntentPickUri.launch(intentConRespuesta)
            }
        val botonIntentExplicito = findViewById<Button>(R.id.btn_ir_intent_explicito)
        botonIntentExplicito
            .setOnClickListener {
                abrirActividadConParametros(ClnIntentExplicitoParametros::class.java)
            }

        val botonSqlite = findViewById<Button>(R.id.btn_SQlite)
        botonSqlite
            .setOnClickListener{
                irActividad(ECrudEntenador::class.java)
            }

        val botonRView = findViewById<Button>(R.id.btn_reccycle_view)
        botonRView
            .setOnClickListener{
                irActividad(FRecyclerView::class.java)
            }
        val botonGoogleMaps = findViewById<Button>(R.id.btn_google_maps)
        botonGoogleMaps
            .setOnClickListener {
                irActividad(GGoogleMapsActivity::class.java)
            }

        val botonFirebaseUI = findViewById<Button>(R.id.btn_intent_firebase_ui)
        botonFirebaseUI
            .setOnClickListener {
                irActividad(HFirebaseUIAuth::class.java)
            }
}
fun abrirActividadConParametros(
    clase: Class<*>
){
    val intentExplicito = Intent(this, clase)
    //Enviar parametros (solamente variables primitivas)
    intentExplicito.putExtra("nombre", "Elisa")
    intentExplicito.putExtra("apellido", "Herrera")
    intentExplicito.putExtra("edad", 28)


    //callbackContenidoIntentExplicito.launch(intentExplicito)
}
    @SuppressLint("SuspiciousIndentation")
    fun irActividad(clase:Class<*>){
        val intent = Intent (this, clase)
        startActivity(intent)

    }
}
