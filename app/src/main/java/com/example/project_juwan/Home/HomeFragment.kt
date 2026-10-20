package com.example.project_juwan.Home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.content.Intent
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project_juwan.R
import com.example.project_juwan.databinding.FragmentHomeBinding
import com.example.project_juwan.WebViewActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Calendar

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = requireActivity().getSharedPreferences("session", Context.MODE_PRIVATE)
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        // Sapaan sesuai jam
        val jam = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val salam = when {
            jam < 11 -> "Selamat Pagi,"
            jam < 15 -> "Selamat Siang,"
            jam < 18 -> "Selamat Sore,"
            else -> "Selamat Malam,"
        }
        binding.tvSalam.text = salam

        val email = prefs.getString("email", "") ?: ""
        binding.tvNama.text = "Hallo, ${email.substringBefore("@")}"

        // Tombol kecil -> pindah tab
        binding.btnKonsultasi.setOnClickListener {
            bottomNav.selectedItemId = R.id.nav_konsultasi
        }
        binding.btnRekam.setOnClickListener {
            bottomNav.selectedItemId = R.id.nav_rekam
        }

        // WebView
        // Tombol Artikel -> halaman WebView
        binding.btnArtikel.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}