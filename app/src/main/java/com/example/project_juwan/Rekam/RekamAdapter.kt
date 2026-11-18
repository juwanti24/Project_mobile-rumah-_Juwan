package com.example.project_juwan.Rekam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_juwan.databinding.ItemRekamBinding
import android.util.Log

class RekamAdapter(
    private val list: List<RekamMedis>,
    private val onClick: (RekamMedis) -> Unit
) : RecyclerView.Adapter<RekamAdapter.VH>() {

    class VH(val b: ItemRekamBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemRekamBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val r = list[position]
        holder.b.tvTanggal.text = r.tanggal
        holder.b.tvJudul.text = r.judul
        holder.b.tvDokter.text = r.dokter
        holder.b.root.setOnClickListener { onClick(r) }
        Log.d("RekamAdapter", "Bind posisi $position: ${r.judul} - ${r.dokter}")
    }

    override fun getItemCount(): Int {
        Log.d("RekamAdapter", "Jumlah data: ${list.size}")
        return list.size
    }
}