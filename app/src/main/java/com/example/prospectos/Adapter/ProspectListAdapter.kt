package com.example.prospectos.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.prospectos.R

class ProspectListAdapter(
    context: Context,
    private val prospectList: List<Prospect>
) : ArrayAdapter<Prospect>(context, 0, prospectList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val prospect = prospectList[position]

        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_prospecto,
            parent,
            false
        )

        val textViewNombre = view.findViewById<TextView>(R.id.textViewNombre)
        val textViewApellidos = view.findViewById<TextView>(R.id.textViewApellidos)
        val textViewEstatus = view.findViewById<TextView>(R.id.textViewEstatus)

        textViewNombre.text = prospect.nombre
        textViewApellidos.text = "${prospect.apep} ${prospect.apem}"
        textViewEstatus.text = prospect.estatus

        return view
    }
}
