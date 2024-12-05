package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SolicitanteActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.solicitante)



        /*method is used to give gradient color animation to the bottom of profile screen
        val BotonSolicitantes = findViewById<Button>(R.id.btnSolicitante)
        val BotonReclutadores = findViewById<Button>(R.id.btnEmpleador)

        BotonSolicitantes.setOnClickListener{
            Toast.makeText(this, "¡Botón presionado!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SolicitanteActivity::class.java)
            startActivity(intent)
        }*/


    }
}