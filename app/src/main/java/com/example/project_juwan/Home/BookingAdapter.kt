package com.example.project_juwan.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_juwan.data.entity.BookingEntity
import com.example.project_juwan.databinding.ItemBookingBinding

class BookingAdapter(
    private var list: List<BookingEntity>,
    private val onClick: (BookingEntity) -> Unit
) : RecyclerView.Adapter<BookingAdapter.VH>() {

    class VH(val b: ItemBookingBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemBookingBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val d = list[position]
        holder.b.tvDokter.text = d.namaDokter
        holder.b.tvWaktu.text = "${d.tanggalLengkap()} • ${d.jam}"
        holder.b.tvKeluhan.text = "Keluhan: ${d.keluhan}"
        holder.b.tvStatus.text = "● ${d.status}"
        holder.b.root.setOnClickListener { onClick(d) }
    }

    override fun getItemCount() = list.size

    fun updateData(newList: List<BookingEntity>) {
        list = newList
        notifyDataSetChanged()
    }
}