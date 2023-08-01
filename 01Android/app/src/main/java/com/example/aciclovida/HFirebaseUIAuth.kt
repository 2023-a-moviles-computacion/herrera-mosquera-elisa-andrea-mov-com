package com.example.aciclovida

import android.app.Activity.RESULT_OK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle

import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button

import androidx.constraintlayout.widget.ConstraintSet.INVISIBLE
import androidx.constraintlayout.widget.ConstraintSet.VISIBLE
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.firestore.core.View

class HFirebaseUIAuth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hfirebase_uiauth)
    }

    private val respuestaLoginAuthUI = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res : FirebaseAuthUIAuthenticationResult ->
        if (res.resultCode === RESULT_OK) {
            if (res.idpResponse != null) {
                seLoogeo(res.idpResponse!!)
            }
        }
    }

    fun seLoogeo(
        res: IdpResponse
    ) {
        val btnLogin: Button = findViewById<Button>(R.id.btn_login_firebase)
        val btnLogout = findViewById<Button>(R.id.btn_logout_firebase)
        btnLogout.visibility = android.view.View.VISIBLE
        btnLogin.visibility = android.view.View.INVISIBLE
        if (res.isNewUser == true) {
            registrarUsuarioPorPrimeraVez(res)
        }
    }

    fun registrarUsuarioPorPrimeraVez(usuario: IdpResponse){
        /*usuario.email;
        usuario.phoneNumber;
        usuario.user.name
         */
    }
}


