package com.example.project_juwan.Rekam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.project_juwan.databinding.ItemRekamBinding

class RekamAdapter(
    context: Context,
    private val data: List<RekamMedis>
) : ArrayAdapter<RekamMedis>(context, 0, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = if (convertView == null) {
            ItemRekamBinding.inflate(LayoutInflater.from(context), parent, false)
        } else {
            ItemRekamBinding.bind(convertView)
        }

        val rekam = data[position]
        binding.imgIkon.setImageResource(rekam.ikon)
        binding.tvTanggal.text = rekam.tanggal
        binding.tvJudul.text = rekam.judul
        binding.tvDokter.text = "${rekam.dokter} · ${rekam.spesialis}"
        binding.tvCatatan.text = rekam.catatan

        return binding.root
    }
}