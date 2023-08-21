package com.example.prospectos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.prospectos.Adapter.Prospect

class DetalleProspectoActivity : AppCompatActivity() {

    private lateinit var prospect: Prospect

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_prospecto)

        prospect = intent.getSerializableExtra("prospect") as Prospect

        val textViewNombre = findViewById<TextView>(R.id.textViewNombre)
        val textViewApellidos = findViewById<TextView>(R.id.textViewApellidos)
        val textViewEstatus = findViewById<TextView>(R.id.textViewEstatus)
        val textViewCalle = findViewById<TextView>(R.id.textViewCalle)
        val textViewNum = findViewById<TextView>(R.id.textViewNum)
        val textViewCol = findViewById<TextView>(R.id.textViewCol)
        val textViewTel = findViewById<TextView>(R.id.textViewTelefono)
        val textViewObservaciones = findViewById<TextView>(R.id.textViewObservaciones)


        textViewNombre.text = "Nombres: "+prospect.nombre
        textViewApellidos.text = "Apellidos: "+"${prospect.apep} ${prospect.apem}"
        textViewCalle.text = "Calle: "+prospect.calle
        textViewNum.text = "Numero: "+prospect.num
        textViewCol.text = "Colonia: "+prospect.col
        textViewTel.text = "Telefono: "+prospect.tel
        textViewEstatus.text = "Estatus: "+prospect.estatus

        if (prospect.estatus == "Rechazado") {
            // Show observations for rejected prospect
            textViewObservaciones.text = "Observaciones: Prospecto rechazado"
            textViewObservaciones.visibility = View.VISIBLE
        } else {
            textViewObservaciones.visibility = View.GONE
        }
    }
}