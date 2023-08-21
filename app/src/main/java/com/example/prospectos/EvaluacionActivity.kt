package com.example.prospectos

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.prospectos.Adapter.Prospect
import com.example.prospectos.Adapter.ProspectListAdapter2
import com.example.prospectos.DB.DBHelper

class EvaluacionActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var listViewProspects: ListView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluacion)
        dbHelper = DBHelper(this)

        listViewProspects = findViewById(R.id.listViewProspects)

        val prospectList2 = retrieveProspectsFromDatabase()
        val adapter = ProspectListAdapter2(this, dbHelper, prospectList2) // Pass dbHelper instance
        listViewProspects.adapter = adapter
    }


    @SuppressLint("Range")
    private fun retrieveProspectsFromDatabase(): List<Prospect> {
        val prospectList = mutableListOf<Prospect>()

        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DBHelper.TABLE_NAME,
            arrayOf(
                DBHelper.COLUMN_ID,
                DBHelper.COLUMN_NOMBRE,
                DBHelper.COLUMN_APEP,
                DBHelper.COLUMN_APEM,
                DBHelper.COLUMN_CALLE,
                DBHelper.COLUMN_NUM,
                DBHelper.COLUMN_COL,
                DBHelper.COLUMN_TEL,
                DBHelper.COLUMN_RFC,
                DBHelper.COLUMN_AUT
            ),
            null,
            null,
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_ID))
            val nombre = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NOMBRE))
            val apep = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_APEP))
            val apem = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_APEM))
            val calle = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_CALLE))
            val num = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NUM))
            val col = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_COL))
            val tel = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TEL))
            val rfc = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_RFC))
            val estatus = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_AUT))

            val prospect = Prospect(id, nombre, apep, apem, calle, num, col , tel , rfc, estatus)
            prospectList.add(prospect)
        }

        cursor.close()
        return prospectList
    }
}