package com.example.prospectos.Adapter

import java.io.Serializable

data class Prospect(
    val id: Long,
    val nombre: String,
    val apep: String,
    val apem: String,
    val calle: String,
    val num: String,
    val col: String,
    val tel: String,
    val rfc: String,
    val estatus: String
) : Serializable
