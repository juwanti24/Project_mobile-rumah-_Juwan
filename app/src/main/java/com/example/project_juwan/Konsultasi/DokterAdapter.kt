package com.example.project_juwan.Konsultasi

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.project_juwan.databinding.ItemDokterBinding

class DokterAdapter(
    context: Context,
    private val data: List<Dokter>,
    private val onDetailClick: (Dokter) -> Unit
) : ArrayAdapter<Dokter>(context, 0, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = if (convertView == null) {
            ItemDokterBinding.inflate(LayoutInflater.from(context), parent, false)
        } else {
            ItemDokterBinding.bind(convertView)
        }

        val dokter = data[position]
        binding.tvNama.text = dokter.nama
        binding.tvSpesialis.text = "Spesialis ${dokter.spesialis}"
        binding.tvRating.text = "★ ${dokter.rating}/5"

        Glide.with(context)
            .load(dokter.foto)
            .override(200, 200)
            .centerCrop()
            .into(binding.imgDokter)

        if (dokter.tersedia) {
            binding.tvStatus.text = "● Tersedia"
            binding.tvStatus.setTextColor(Color.parseColor("#1C8E38"))
        } else {
            binding.tvStatus.text = "● Penuh"
            binding.tvStatus.setTextColor(Color.GRAY)
        }

        binding.btnDetail.setOnClickListener { onDetailClick(dokter) }

        return binding.root
    }
}