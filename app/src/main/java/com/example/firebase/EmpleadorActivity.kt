package com.example.firebase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EmpleadorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.empleador)

        val botonSubmit = findViewById<Button>(R.id.submitButton)
        val companyNameText = findViewById<EditText>(R.id.companyName)
        val jobTitleText = findViewById<EditText>(R.id.jobTitle)
        val estimatedSalaryText = findViewById<EditText>(R.id.estimatedSalary)
        val descriptionText = findViewById<EditText>(R.id.description)

        botonSubmit.setOnClickListener {
            // Obtiene los valores de los campos de texto
            val companyName = companyNameText.text.toString().trim()
            val jobTitle = jobTitleText.text.toString().trim()
            val estimatedSalary = estimatedSalaryText.text.toString().trim()
            val description = descriptionText.text.toString().trim()

            // Verifica que los campos no estén vacíos
            if (companyName.isEmpty() || jobTitle.isEmpty() || estimatedSalary.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Escribe en la base de datos Firebase usando el nombre de la empresa como clave
            val database = Firebase.database
            val myRef = database.getReference("reclutadores")

            val empleadorData = mapOf(
                "companyName" to companyName, // Incluye el nombre de la empresa como dato
                "jobTitle" to jobTitle,
                "estimatedSalary" to estimatedSalary,
                "description" to description
            )

            // Usa el nombre de la empresa como clave para el nodo
            myRef.child(companyName).setValue(empleadorData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Datos enviados exitosamente", Toast.LENGTH_SHORT).show()
                    // Limpia los campos después de guardar
                    companyNameText.text.clear()
                    jobTitleText.text.clear()
                    estimatedSalaryText.text.clear()
                    descriptionText.text.clear()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al enviar los datos: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
