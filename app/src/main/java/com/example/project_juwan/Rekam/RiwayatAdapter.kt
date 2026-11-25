package com.example.project_juwan.Rekam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_juwan.data.entity.BookingEntity
import com.example.project_juwan.databinding.ItemRekamBinding

class RiwayatAdapter(
    private var list: List<BookingEntity>,
    private val onClick: (BookingEntity) -> Unit
) : RecyclerView.Adapter<RiwayatAdapter.VH>() {

    class VH(val b: ItemRekamBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemRekamBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val d = list[position]
        holder.b.tvTanggal.text = "${d.tanggalLengkap()} • ${d.jam}"
        holder.b.tvJudul.text = d.keluhan
        holder.b.tvDokter.text = d.namaDokter
        holder.b.root.setOnClickListener { onClick(d) }
    }

    override fun getItemCount() = list.size

    fun updateData(newList: List<BookingEntity>) {
        list = newList
        notifyDataSetChanged()
    }
}