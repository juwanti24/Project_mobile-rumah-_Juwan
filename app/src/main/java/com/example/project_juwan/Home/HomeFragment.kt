package com.example.project_juwan.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_juwan.R
import android.widget.Toast
import com.example.project_juwan.WebViewActivity
import com.example.project_juwan.data.AppDatabase
import com.example.project_juwan.data.entity.BookingEntity
import com.example.project_juwan.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.collections.firstOrNull

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase
    private lateinit var adapter: BookingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())

        val prefs = requireActivity().getSharedPreferences("session", Context.MODE_PRIVATE)
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        val email = prefs.getString("email", "") ?: ""
        binding.tvNama.text = "Hallo, ${email.substringBefore("@")}"

        // Daftar semua booking
        adapter = BookingAdapter(emptyList()) { booking -> tampilDetail(booking) }
        binding.rvBooking.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBooking.adapter = adapter

        binding.btnKonsultasi.setOnClickListener {
            bottomNav.selectedItemId = R.id.nav_konsultasi
        }
        binding.btnRekam.setOnClickListener {
            bottomNav.selectedItemId = R.id.nav_rekam
        }
        binding.btnProfil.setOnClickListener {
            bottomNav.selectedItemId = R.id.nav_profil
        }
        binding.btnArtikel.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }
    }

    // Dimuat ulang setiap kembali ke Home
    override fun onResume() {
        super.onResume()
        if (_binding != null) muatBooking()
    }

    private fun muatBooking() {
        viewLifecycleOwner.lifecycleScope.launch {
            val semua = db.bookingDao().getAktif()   // hanya yang berstatus Terjadwal
            val bind = _binding ?: return@launch

            adapter.updateData(semua)
            bind.tvBookingKosong.visibility = if (semua.isEmpty()) View.VISIBLE else View.GONE

            val terdekat = semua.firstOrNull()
            if (terdekat == null) {
                bind.tvTanggalBadge.text = "-"
                bind.tvDokterNext.text = "Belum ada booking"
                bind.tvJamNext.text = "Pilih dokter di menu Konsultasi"
                bind.cardKonsultasiNext.setOnClickListener(null)
                bind.cardKonsultasiNext.isClickable = false
                return@launch
            }

            val tanggal = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(terdekat.tanggal)
            val localeId = Locale("id", "ID")
            if (tanggal != null) {
                val bulan = SimpleDateFormat("MMM", localeId).format(tanggal).uppercase()
                val tgl = SimpleDateFormat("dd", localeId).format(tanggal)
                val hari = SimpleDateFormat("EEEE", localeId).format(tanggal)
                bind.tvTanggalBadge.text = "$bulan\n$tgl"
                bind.tvJamNext.text = "$hari, ${terdekat.jam}"
            }
            bind.tvDokterNext.text = terdekat.namaDokter

            // Klik kartu terdekat -> detail booking
            bind.cardKonsultasiNext.setOnClickListener { tampilDetail(terdekat) }
        }
    }

    private fun tampilDetail(booking: BookingEntity) {
        val pesan = "Spesialis: ${booking.spesialis}\n" +
                "Keluhan: ${booking.keluhan}\n" +
                "Tanggal: ${booking.tanggalLengkap()}\n" +
                "Jam: ${booking.jam}\n" +
                "Status: ${booking.status}"

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(booking.namaDokter)
            .setMessage(pesan)
            .setPositiveButton("Selesai") { _, _ -> tandaiSelesai(booking) }
            .setNeutralButton("Batalkan Booking") { _, _ -> konfirmasiBatal(booking) }
            .setNegativeButton("Tutup", null)
            .show()
    }
    private fun konfirmasiBatal(booking: BookingEntity) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Batalkan Booking")
            .setMessage("Yakin ingin membatalkan konsultasi dengan ${booking.namaDokter}?")
            .setPositiveButton("Ya") { _, _ ->
                viewLifecycleOwner.lifecycleScope.launch {
                    db.bookingDao().delete(booking)
                    muatBooking()
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun tandaiSelesai(booking: BookingEntity) {
        viewLifecycleOwner.lifecycleScope.launch {
            db.bookingDao().update(booking.copy(status = "Selesai"))
            Toast.makeText(
                requireContext(),
                "Booking selesai dan masuk ke Rekam Medis",
                Toast.LENGTH_SHORT
            ).show()
            muatBooking()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}