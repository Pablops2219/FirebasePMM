package com.example.firebase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SolicitanteActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.solicitante)

        // Referencias a los elementos del formulario
        val firstNameText = findViewById<EditText>(R.id.firstName)
        val lastNameText = findViewById<EditText>(R.id.lastName)
        val positionSpinner = findViewById<Spinner>(R.id.position)
        val contactNumberText = findViewById<EditText>(R.id.contactNumber)
        val skillsText = findViewById<EditText>(R.id.skills)
        val submitButton = findViewById<Button>(R.id.submitButton)

        // Configuración del botón de enviar
        submitButton.setOnClickListener {
            // Obtener los valores del formulario
            val firstName = firstNameText.text.toString().trim()
            val lastName = lastNameText.text.toString().trim()
            val position = positionSpinner.selectedItem?.toString()?.trim()
            val contactNumber = contactNumberText.text.toString().trim()
            val skills = skillsText.text.toString().trim()

            // Validar que todos los campos estén completos
            if (firstName.isEmpty() || lastName.isEmpty() || position.isNullOrEmpty() || contactNumber.isEmpty() || skills.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Referencia a la base de datos Firebase
            val database = Firebase.database
            val myRef = database.getReference("solicitantes")

            // Crear un mapa con los datos del solicitante
            val solicitanteData = mapOf(
                "contactNumber" to contactNumber,
                "firstName" to firstName,
                "lastName" to lastName,
                "position" to position,
                "skills" to skills
            )

            // Generar una clave única para cada solicitante
            //val applicantKey = applicantsRef.push().key ?: ""

            // Subir los datos a Firebase
            myRef.child(firstName).setValue(solicitanteData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Datos enviados exitosamente", Toast.LENGTH_SHORT).show()

                    // Limpiar los campos del formulario
                    firstNameText.text.clear()
                    lastNameText.text.clear()
                    contactNumberText.text.clear()
                    skillsText.text.clear()
                    positionSpinner.setSelection(0)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al enviar los datos: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

    }
}