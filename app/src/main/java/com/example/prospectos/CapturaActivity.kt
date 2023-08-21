package com.example.prospectos

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.prospectos.DB.DBHelper
import com.example.prospectos.databinding.ActivityCapturaBinding
import com.example.prospectos.databinding.ActivityMainBinding

class CapturaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCapturaBinding
    private lateinit var dbHelper: DBHelper

    private val documentLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        uri?.let { selectedDocumentUri ->
            // mostrar el nombre del archivo seleccionado
            val selectedDocumentName = contentResolver.query(selectedDocumentUri, null, null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    cursor.getString(cursor.getColumnIndexOrThrow("_display_name"))
                } else {
                    null
                }
            }

            // agregar mas codigo si requiero alguna otra funcion por el momento lo omito ya que es para el examen
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCapturaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = DBHelper(this)

        binding.btnSelectDocument.setBackgroundColor(Color.GRAY)

        binding.btnSelectDocument.setOnClickListener {
            launchDocumentPicker()
        }

        binding.button.setOnClickListener {
            val nombre = binding.etNom.text.toString()
            val apep   = binding.etApep.text.toString()
            val apem   = binding.etApem.text.toString()
            val calle  = binding.etCalle.text.toString()
            val num    = binding.etNumCasa.text.toString()
            val col    = binding.etCol.text.toString()
            val cp     = binding.etCP.text.toString()
            val tel    = binding.etTelefono.text.toString()
            val rfc    = binding.etRFC.text.toString()
            insertDatos(nombre,apep, apem, calle, num, col, cp, tel , rfc)
        }


    }

    private fun insertDatos(
        nombre: String,
        apep: String,
        apem: String,
        calle: String,
        num: String,
        col: String,
        cp: String,
        tel: String,
        rfc: String
    ) {
        val db = dbHelper.writableDatabase
        val id = dbHelper.insertData(nombre, apep, apem, calle, num, col, cp, tel, rfc)
        if (id != -1L) {
            val successDialog = AlertDialog.Builder(this)
                .setTitle("Guardado Exitoso")
                .setMessage("Los datos se han guardado correctamente.")
                .setPositiveButton("Aceptar") { _, _ ->
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                .create()

            successDialog.show()


        } else {
            Toast.makeText(this, "Error inserting data", Toast.LENGTH_SHORT).show()
        }

    }

    private fun guardadoExito() {
        TODO("Not yet implemented")
    }


    private fun launchDocumentPicker() {
        val mimeTypes = arrayOf("application/pdf", "application/msword", "image/*")
        documentLauncher.launch(mimeTypes)
    }
}