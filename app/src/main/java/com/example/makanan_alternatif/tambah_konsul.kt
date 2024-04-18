package com.example.makanan_alternatif

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter

class tambah_konsul : AppCompatActivity() {
    val arrayPasien = arrayOf("001, devi","002, vanesa", "003, anggi", "004, agung", "005, budi")
    val arrayMakanan = arrayOf("Nasi", "Gandum", "Apel", "Bayam")

    lateinit var adapterActv: ArrayAdapter<String>
    lateinit var adapterSpin: ArrayAdapter<String>
    lateinit var adapterLv: ArrayAdapter<String>
    lateinit var arrayListGabungan: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}