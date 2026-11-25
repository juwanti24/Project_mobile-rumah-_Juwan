package com.example.project_juwan.Rekam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_juwan.data.AppDatabase
import com.example.project_juwan.data.entity.BookingEntity
import com.example.project_juwan.databinding.FragmentRiwayatBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class RekamListFragment : Fragment() {
    private var _binding: FragmentRiwayatBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase
    private lateinit var adapter: RiwayatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRiwayatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())
        adapter = RiwayatAdapter(emptyList()) { booking -> tampilDetail(booking) }
        binding.rvRiwayat.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRiwayat.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        if (_binding != null) muatRiwayat()
    }

    private fun muatRiwayat() {
        viewLifecycleOwner.lifecycleScope.launch {
            val hasil = db.bookingDao().getRiwayat()
            val bind = _binding ?: return@launch
            adapter.updateData(hasil)
            bind.tvKosong.visibility = if (hasil.isEmpty()) View.VISIBLE else View.GONE
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
            .setPositiveButton("Tutup", null)
            .show()
    }

    override fun onDestroyView() {
        binding.rvRiwayat.adapter = null
        _binding = null
        super.onDestroyView()
    }
}