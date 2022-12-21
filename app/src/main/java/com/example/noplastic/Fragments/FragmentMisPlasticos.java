package com.example.noplastic.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noplastic.InformacionPlasticos.ListPlasticos;
import com.example.noplastic.R;
import com.example.noplastic.db.DbPlastico;

import java.util.ArrayList;


public class FragmentMisPlasticos extends Fragment {
    RecyclerView listaPlasticos;
    ArrayList<ListPlasticos> listaP;

    public FragmentMisPlasticos() {
        // Required empty public constructor
    }

    public static FragmentMisPlasticos newInstance(String param1, String param2) {
        FragmentMisPlasticos fragment = new FragmentMisPlasticos();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_lista, container, false);
        listaPlasticos= vista.findViewById(R.id.rMisPlasticos);
        listaPlasticos.setLayoutManager(new LinearLayoutManager(getContext()));
        DbPlastico dbPlastico=new DbPlastico(getContext());
        listaP=new ArrayList<>();
        MisPlasticosAdapter adapter=new MisPlasticosAdapter(dbPlastico.mostrarPlasticos());
        listaPlasticos.setAdapter(adapter);


        return vista;
    }
}