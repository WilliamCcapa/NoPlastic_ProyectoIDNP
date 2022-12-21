package com.example.noplastic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.noplastic.Fragments.*
import com.google.android.material.tabs.TabLayout

class SegundoActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.segundo_main)
        var viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabLayout=findViewById<TabLayout>(R.id.tabMenu)
        val fragmentAdapter= FragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(FragmentInfo(),"Info")
        fragmentAdapter.addFragment(FragmentRegistro(),"Registro")
        fragmentAdapter.addFragment(FragmentMisPlasticos (),"Mis plásticos")
        fragmentAdapter.addFragment(FragmentEstadisticas(),"Gráficos")
        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}