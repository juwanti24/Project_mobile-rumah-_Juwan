package com.example.project_juwan.Rekam

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project_juwan.DetailActivity
import com.example.project_juwan.databinding.FragmentDaftarBinding

class RekamListFragment : Fragment() {
    private var _binding: FragmentDaftarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaftarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvList.adapter = RekamAdapter(RekamData.daftarRekam) { r ->
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