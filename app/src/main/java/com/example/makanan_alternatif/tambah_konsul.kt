package com.example.makanan_alternatif

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.makanan_alternatif.databinding.ActivityTambahKonsulBinding
import java.util.Calendar

class tambah_konsul : AppCompatActivity() {
    private lateinit var binding: ActivityTambahKonsulBinding
    val arrayPasien = arrayOf("001, Devi","002, Vanesa", "003, Anggi", "004, Agung", "005, Budi", "115, Anggita", "152, Aprilia")
    val arrayMakanan = arrayOf("Nasi", "Gandum", "Apel", "Bayam")

    lateinit var adapterActvP: ArrayAdapter<String>
    lateinit var adapterActvM: ArrayAdapter<String>

    var tahun = 0
    var bulan = 0
    var hari = 0
    var jam = 0
    var menit = 0
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

        val cal: Calendar = Calendar.getInstance()

        tahun = cal.get(Calendar.YEAR)
        bulan = cal.get(Calendar.MONTH) + 1
        hari = cal.get(Calendar.DAY_OF_MONTH)
        jam = cal.get(Calendar.HOUR)
        menit = cal.get(Calendar.MINUTE)

        // Menampilkan teks awal pada textView3
        updateTextView()

        // Menampilkan DatePickerDialog ketika tombol btndp2 ditekan
        binding.bt1.setOnClickListener {
            showDialog(10)
        }

        // Menampilkan TimePickerDialog ketika tombol btntp2 ditekan
        binding.bt2.setOnClickListener {
            showDialog(20)
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

    private fun updateTextView() {
        binding.tvTanggal.text = "Tanggal $hari, $bulan, $tahun"
        binding.Jam.text = "Jam $jam : $menit"
    }

    override fun onCreateDialog(id: Int): Dialog {
        return when (id) {
            10 -> {
                // Membuat DatePickerDialog dan mengembalikannya
                DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        tahun = year
                        bulan = month + 1
                        hari = dayOfMonth
                        updateTextView()
                    },
                    tahun,
                    bulan - 1,
                    hari
                )
            }
            20 -> {
                // Membuat TimePickerDialog dan mengembalikannya
                TimePickerDialog(
                    this,
                    TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                        jam = hourOfDay
                        menit = minute
                        updateTextView()
                    },
                    jam,
                    menit,
                    true
                )
            }
            else -> super.onCreateDialog(id)
        }
    }
}
