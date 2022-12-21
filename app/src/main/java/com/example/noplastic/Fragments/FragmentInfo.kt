package com.example.noplastic.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noplastic.R
import androidx.recyclerview.widget.RecyclerView
import com.example.noplastic.InformacionPlasticos.plasticProvider
import com.example.noplastic.adapter.Adapter

class FragmentInfo : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View=inflater.inflate(R.layout.fragment_info, container, false)
        val recyclerView=view.findViewById<RecyclerView>(R.id.recyclerPlastico)
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=Adapter(plasticProvider.listaPlastico)
        return view
    }
}