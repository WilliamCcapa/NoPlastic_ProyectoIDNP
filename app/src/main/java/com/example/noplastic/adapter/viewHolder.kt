package com.example.noplastic.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.noplastic.InformacionPlasticos.Plastico
import com.example.noplastic.R

class viewHolder(view:View):RecyclerView.ViewHolder(view) {
    val plastico=view.findViewById<TextView>(R.id.tvNombrePlastico)
    val descripcion=view.findViewById<TextView>(R.id.tvDescripcion)
    val imagen=view.findViewById<ImageView>(R.id.ivTipoPlastico)
    val expansivoLayout=view.findViewById<ConstraintLayout>(R.id.layout_expansible)

    fun render(plasticos: Plastico){

        plastico.text=plasticos.plastico
        expansivoLayout.visibility=View.GONE
        plastico.setCompoundDrawablesRelativeWithIntrinsicBounds(
            plasticos.icono,0,  R.drawable.ic_baseline_arrow_drop_down_24,0
        )
        plastico.setOnClickListener{
            if(plasticos.visible){

                plastico.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    plasticos.icono,0,  R.drawable.ic_baseline_arrow_drop_down_24,0
                )
                expansivoLayout.visibility=View.GONE
            }
            else{
                plastico.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    plasticos.icono,0, R.drawable.ic_baseline_arrow_drop_up_24,0
                )
                expansivoLayout.visibility=View.VISIBLE
            }
            plasticos.visible=!plasticos.visible
        }
        descripcion.text=plasticos.descripcion
        imagen.setImageResource(plasticos.imagen)

    }
}