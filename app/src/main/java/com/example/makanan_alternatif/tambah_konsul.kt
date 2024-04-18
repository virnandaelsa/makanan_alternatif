package com.example.makanan_alternatif

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.makanan_alternatif.databinding.ActivityTambahKonsulBinding

class tambah_konsul : AppCompatActivity() {
    private lateinit var binding: ActivityTambahKonsulBinding
    val arrayPasien = arrayOf("001, Devi","002, Vanesa", "003, Anggi", "004, Agung", "005, Budi", "115, Anggita", "152, Aprilia")
    val arrayMakanan = arrayOf("Nasi", "Gandum", "Apel", "Bayam")

    lateinit var adapterActvP: ArrayAdapter<String>
    lateinit var adapterActvM: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahKonsulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set adapter untuk AutoCompleteTextView nomor pasien
        adapterActvP = ArrayAdapter(this, android.R.layout.simple_list_item_1, getNomorArray())
        binding.actNopasien.setAdapter(adapterActvP)

        // Set adapter untuk AutoCompleteTextView makanan
        adapterActvM = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayMakanan)
        binding.actMakanan.setAdapter(adapterActvM)

        // Set threshold untuk AutoCompleteTextView nomor pasien
        binding.actNopasien.threshold = 2

        // Menangani pemilihan item dalam AutoCompleteTextView nomor pasien
        binding.actNopasien.setOnItemClickListener { parent, view, position, id ->
            val selectedPasien = parent.getItemAtPosition(position).toString()
            val nomorPasien = selectedPasien.split(", ")[0]
            val namaPasien = cariNamaPasien(nomorPasien)
            binding.tv5.text = namaPasien
        }
    }

    // Fungsi untuk mendapatkan array nomor pasien dari arrayPasien
    private fun getNomorArray(): ArrayList<String> {
        val listNomor = ArrayList<String>()
        for (pasien in arrayPasien) {
            val nomor = pasien.split(", ")[0]
            listNomor.add(nomor)
        }
        return listNomor
    }

    // Fungsi untuk mencari nama pasien berdasarkan nomor pasien
    private fun cariNamaPasien(nomorPasien: String): String {
        for (pasien in arrayPasien) {
            val nomor = pasien.split(", ")[0]
            if (nomor == nomorPasien) {
                return pasien.split(", ")[1]
            }
        }
        return "" // Jika nomor pasien tidak ditemukan
    }
}
