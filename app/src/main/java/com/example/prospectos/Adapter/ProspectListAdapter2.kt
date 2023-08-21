package com.example.prospectos.Adapter

import android.content.ContentValues
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.prospectos.DB.DBHelper
import com.example.prospectos.R

class ProspectListAdapter2(
    context: Context,
    private val dbHelper: DBHelper,
    private val prospectList2: List<Prospect>
) : ArrayAdapter<Prospect>(context, 0, prospectList2) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val prospect = prospectList2[position]

        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_prospect,
            parent,
            false
        )

        val textViewNombre = view.findViewById<TextView>(R.id.textViewNombre)
        val textViewApellidos = view.findViewById<TextView>(R.id.textViewApellidos)
        val textViewRfc = view.findViewById<TextView>(R.id.textViewRfc)
        val editTextObservaciones = view.findViewById<EditText>(R.id.editTextObservaciones)
        val buttonAutorizar = view.findViewById<Button>(R.id.buttonAutorizar)
        val buttonRechazar = view.findViewById<Button>(R.id.buttonRechazar)

        textViewNombre.text = prospect.nombre
        textViewApellidos.text = "${prospect.apep} ${prospect.apem}"
        textViewRfc.text = prospect.rfc

        buttonAutorizar.setOnClickListener {
            val db = dbHelper.writableDatabase

            val values = ContentValues().apply {
                put(DBHelper.COLUMN_AUT, "APROBADO")
            }

            val updatedRows = db.update(
                DBHelper.TABLE_NAME,
                values,
                "${DBHelper.COLUMN_ID} = ?",
                arrayOf(prospect.id.toString())
            )

            if (updatedRows > 0) {
                Toast.makeText(context, "Prospecto Aprobado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error al aprobar el prospecto", Toast.LENGTH_SHORT).show()
            }
        }

        buttonRechazar.setOnClickListener {
            val observaciones = editTextObservaciones.text.toString()
            if (observaciones.isBlank()) {
                editTextObservaciones.error = "Este campo es obligatorio"
            } else {
                val db = dbHelper.writableDatabase

                val values = ContentValues().apply {
                    put(DBHelper.COLUMN_AUT, "RECHAZADO")
                }

                val updatedRows = db.update(
                    DBHelper.TABLE_NAME,
                    values,
                    "${DBHelper.COLUMN_ID} = ?",
                    arrayOf(prospect.id.toString())
                )

                if (updatedRows > 0) {

                    Toast.makeText(context, "Prospecto Rechazado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Error al Rechazar el prospecto", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view
    }
}