package com.example.prueba1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        variablesYconstantes()
    }

    private fun variablesYconstantes(){
        //variables
        var myVariable = "hello word"
        println(myVariable)
        //imprime en pantalla
        
    }

}