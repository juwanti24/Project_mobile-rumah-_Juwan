package com.example.project_juwan.Rekam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.project_juwan.R
import com.example.project_juwan.databinding.FragmentRekamBinding
import com.google.android.material.tabs.TabLayoutMediator
import androidx.lifecycle.lifecycleScope
import com.example.project_juwan.data.AppDatabase
import kotlinx.coroutines.launch

class RekamFragment : Fragment() {
    private var _binding: FragmentRekamBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRekamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = RekamTabsAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Rekam Medis"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_description)
                }
                1 -> {
                    tab.text = "Obat"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_medication)
                    // Badge angka jumlah obat
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = RekamData.daftarObat.size
                }
            }
        }.attach()
    }

    override fun onResume() {
        super.onResume()
        if (_binding != null) muatJumlah()
    }

    private fun muatJumlah() {
        viewLifecycleOwner.lifecycleScope.launch {
            val jumlah = AppDatabase.getInstance(requireContext()).bookingDao().getRiwayat().size
            _binding?.tvJumlah?.text = "$jumlah catatan medis"
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}