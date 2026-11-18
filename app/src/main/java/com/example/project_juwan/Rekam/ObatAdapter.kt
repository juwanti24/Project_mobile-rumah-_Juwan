package com.example.project_juwan.Rekam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_juwan.databinding.ItemObatBinding

class ObatAdapter(private val list: List<Obat>) :
    RecyclerView.Adapter<ObatAdapter.VH>() {

    class VH(val b: ItemObatBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemObatBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val o = list[position]
        holder.b.tvNama.text = o.nama
        holder.b.tvDosis.text = "Dosis: ${o.dosis}"
        holder.b.tvJadwal.text = o.jadwal
        holder.b.tvCatatan.text = o.catatan
    }

    override fun getItemCount() = list.size
}