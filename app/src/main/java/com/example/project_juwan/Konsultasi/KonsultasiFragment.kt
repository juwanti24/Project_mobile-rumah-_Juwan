package com.example.project_juwan.Konsultasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.project_juwan.R
import com.example.project_juwan.databinding.FragmentKonsultasiBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class KonsultasiFragment : Fragment() {
    private var _binding: FragmentKonsultasiBinding? = null
    private val binding get() = _binding!!

    private var spesialisAktif = "Semua"

    // Data dummy
    private val daftarDokter = listOf(
        Dokter("Dr. tirta goat", "Umum", 4.9, true, R.drawable.dr_tirta),
        Dokter("Dr. Siti Rahma", "Anak", 4.8, true, R.drawable.dr_cewek),
        Dokter("Dr. Budi Santoso", "Gigi", 4.5, false, R.drawable.dr_bald),
        Dokter("Dr. Maya Lestari", "Anak", 4.7, true, R.drawable.dr_china),
        Dokter("Dr. Rizky Hidayat", "Umum", 4.6, true, R.drawable.dr_hair)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKonsultasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tampilkanDaftar()

        // Chip filter spesialis
        binding.chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            spesialisAktif = when (checkedIds.firstOrNull()) {
                R.id.chipUmum -> "Umum"
                R.id.chipAnak -> "Anak"
                R.id.chipGigi -> "Gigi"
                else -> "Semua"
            }
            tampilkanDaftar()
        }

        // Pencarian nama
        binding.etCari.doOnTextChanged { _, _, _, _ -> tampilkanDaftar() }
    }

    private fun tampilkanDaftar() {
        val kataKunci = binding.etCari.text.toString().trim()
        val hasil = daftarDokter.filter {
            (spesialisAktif == "Semua" || it.spesialis == spesialisAktif) &&
                    it.nama.contains(kataKunci, ignoreCase = true)
        }
        binding.listDokter.adapter = DokterAdapter(requireContext(), hasil) { dokter ->
            tampilDetail(dokter)
        }
    }

    private fun tampilDetail(dokter: Dokter) {
        val builder = MaterialAlertDialogBuilder(requireActivity())
            .setTitle(dokter.nama)
            .setMessage("Spesialis: ${dokter.spesialis}\nRating: ${dokter.rating}/5")
            .setNegativeButton("Tutup", null)

        if (dokter.tersedia) {
            builder.setPositiveButton("Booking Sekarang") { _, _ ->
                Toast.makeText(
                    requireContext(),
                    "Booking dengan ${dokter.nama} berhasil",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        builder.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}