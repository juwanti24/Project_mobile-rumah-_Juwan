package com.example.project_juwan.Rekam

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.project_juwan.DetailActivity
import com.example.project_juwan.R
import com.example.project_juwan.databinding.FragmentRekamBinding

class RekamFragment : Fragment() {
    private var _binding: FragmentRekamBinding? = null
    private val binding get() = _binding!!

    // Data dummy
    private val daftarRekam = listOf(
        RekamMedis("10 Agustus 2026", "Pemeriksaan rutin", "Dr. Andi Pratama", "Spesialis Umum", "Tekanan darah normal", R.drawable.ic_chat),
        RekamMedis("11 Agustus 2026", "Pemeriksaan gigi", "Dr. Budi Santoso", "Spesialis Gigi", "Gigi berkarang", R.drawable.ic_chat),
        RekamMedis("11 Agustus 2026", "Hasil laboratorium", "Dr. Andi Pratama", "Spesialis Umum", "Hasil tes darah", R.drawable.ic_description),
        RekamMedis("12 Agustus 2026", "Resep obat", "Dr. Siti Rahma", "Spesialis Anak", "Vitamin dan obat pereda demam", R.drawable.ic_description)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRekamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvJumlah.text = "${daftarRekam.size} catatan medis"

        // Ubah data jadi teks per baris untuk simple list item
        val teksList = daftarRekam.map { "${it.tanggal}\n${it.judul} - ${it.dokter}" }

        // ListView dengan simple list item
        binding.listRekam.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            teksList
        )

        // Klik item -> DetailActivity
        binding.listRekam.setOnItemClickListener { _, _, position, _ ->
            val r = daftarRekam[position]
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("judul", "${r.tanggal}\n${r.judul} - ${r.dokter}\n${r.catatan}")
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
