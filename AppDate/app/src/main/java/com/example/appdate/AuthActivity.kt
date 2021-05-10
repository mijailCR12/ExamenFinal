package com.example.appdate

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //aventos personalisados para verlos en consola de firebase
        //Analytics Events
        val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("massage", "Integracion de firebase completa!")
        analytics.logEvent("InitScreen",bundle)

        //Setup
        setup()
    }
    private fun setup(){
        title = "Autenticacion"
        //funcion boton se acceder

        logOutButton.setOnClickListener{
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(emailEditText.text.toString(),
                        passwordEditText.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful){
                            showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                        }else{
                            showAlert()
                        }
                    }
            }
        }
        //funcion boton de registrar
        logInButton.setOnClickListener{
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(),
                    passwordEditText.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email ?:"",ProviderType.BASIC)
                    }else{
                        showAlert()
                    }
                }
            }
        }
    }
    //alerta de error
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //funcion que redirecciona a vista home
    private fun showHome(email: String , provider: ProviderType){
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            //se pasa a la pantalla home
            putExtra("email",email)
            putExtra("provider",provider.name)
        }
        startActivity(homeIntent)
    }
}