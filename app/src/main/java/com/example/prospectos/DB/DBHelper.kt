package com.example.prospectos.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

companion object {
    private const val DATABASE_NAME = "prospectos"
    private const val DATABASE_VERSION = 1

    // Define table and column names
    const val TABLE_NAME = "prospecto"
    const val COLUMN_ID = "id"
    const val COLUMN_NOMBRE = "nombre"
    const val COLUMN_APEP = "apep"
    const val COLUMN_APEM = "apem"
    const val COLUMN_CALLE = "calle"
    const val COLUMN_NUM = "num"
    const val COLUMN_COL = "col"
    const val COLUMN_CP = "cp"
    const val COLUMN_TEL = "tel"
    const val COLUMN_RFC = "rfc"
    const val COLUMN_AUT = "Autorizado"
}

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
        CREATE TABLE $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY,
            $COLUMN_NOMBRE TEXT,
            $COLUMN_APEP TEXT,
            $COLUMN_APEM TEXT,
            $COLUMN_CALLE TEXT,
            $COLUMN_NUM TEXT,
            $COLUMN_COL TEXT,
            $COLUMN_CP TEXT,
            $COLUMN_TEL TEXT,
            $COLUMN_RFC TEXT,
            $COLUMN_AUT TEXT
        )"""
        db?.execSQL(createTableQuery)
    }

override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
}

// CRUD operations
fun insertData(
    nombre: String,
    apep: String,
    apem: String,
    calle: String,
    num: String,
    col: String,
    cp: String,
    tel: String,
    rfc: String
): Long {
    val values = ContentValues().apply {
        put(COLUMN_NOMBRE, nombre)
        put(COLUMN_APEP, apep)
        put(COLUMN_APEM, apem)
        put(COLUMN_CALLE, calle)
        put(COLUMN_NUM, num)
        put(COLUMN_COL, col)
        put(COLUMN_CP, cp)
        put(COLUMN_TEL, tel)
        put(COLUMN_RFC, rfc)
        put(COLUMN_AUT, "PENDIENTE")
    }

    val db = writableDatabase
    return db.insert(TABLE_NAME, null, values)
}

    fun updateProspectAutorizado(id: Long): Int {
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(DBHelper.COLUMN_AUT, "APROBADO")
        }

        return db.update(
            DBHelper.TABLE_NAME,
            values,
            "${DBHelper.COLUMN_ID} = ?",
            arrayOf(id.toString())
        )
    }


}