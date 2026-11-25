package com.example.project_juwan.Konsultasi

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.project_juwan.data.AppDatabase
import com.example.project_juwan.data.entity.BookingEntity
import com.example.project_juwan.databinding.ActivityBookingFormBinding
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Locale

class BookingFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingFormBinding
    private lateinit var db: AppDatabase

    private var namaDokter = ""
    private var spesialis = ""
    private var tanggal = ""   // format simpan: yyyy-MM-dd
    private var jam = ""       // format simpan: HH:mm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        namaDokter = intent.getStringExtra(EXTRA_NAMA) ?: ""
        spesialis = intent.getStringExtra(EXTRA_SPESIALIS) ?: ""
        binding.etDokter.setText("$namaDokter - $spesialis")

        binding.etTanggal.setOnClickListener { pilihTanggal() }
        binding.etJam.setOnClickListener { pilihJam() }

        binding.btnSimpanBooking.setOnClickListener {
            val keluhan = binding.etKeluhan.text.toString().trim()

            if (keluhan.isBlank() || tanggal.isBlank() || jam.isBlank()) {
                Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            binding.btnSimpanBooking.isEnabled = false
            lifecycleScope.launch {
                try {
                    db.bookingDao().insert(
                        BookingEntity(
                            namaDokter = namaDokter,
                            spesialis = spesialis,
                            keluhan = keluhan,
                            tanggal = tanggal,
                            jam = jam
                        )
                    )
                    Toast.makeText(
                        this@BookingFormActivity,
                        "Booking dengan $namaDokter berhasil",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } catch (e: Exception) {
                    binding.btnSimpanBooking.isEnabled = true
                    Toast.makeText(
                        this@BookingFormActivity,
                        "Gagal menyimpan booking",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun pilihTanggal() {
        val kal = Calendar.getInstance()
        val dialog = DatePickerDialog(
            this,
            { _, tahun, bulan, hari ->
                tanggal = String.format(Locale.US, "%04d-%02d-%02d", tahun, bulan + 1, hari)
                binding.etTanggal.setText(
                    String.format(Locale.US, "%02d/%02d/%04d", hari, bulan + 1, tahun)
                )
            },
            kal.get(Calendar.YEAR), kal.get(Calendar.MONTH), kal.get(Calendar.DAY_OF_MONTH)
        )
        dialog.datePicker.minDate = System.currentTimeMillis() - 1000
        dialog.show()
    }

    private fun pilihJam() {
        TimePickerDialog(
            this,
            { _, jamPilih, menit ->
                jam = String.format(Locale.US, "%02d:%02d", jamPilih, menit)
                binding.etJam.setText(jam)
            },
            9, 0, true
        ).show()
    }

    companion object {
        const val EXTRA_NAMA = "nama_dokter"
        const val EXTRA_SPESIALIS = "spesialis"
    }
}