package com.example.project_juwan.Konsultasi

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.project_juwan.R
import com.bumptech.glide.Glide
import com.example.project_juwan.databinding.ItemDokterBinding

class DokterAdapter(
    private var list: List<Dokter>,
    private val onClick: (Dokter) -> Unit
) : RecyclerView.Adapter<DokterAdapter.VH>() {

    class VH(val b: ItemDokterBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemDokterBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val d = list[position]
        val context = holder.itemView.context

        holder.b.tvNama.text = d.nama
        holder.b.tvSpesialis.text = "Spesialis ${d.spesialis}"
        holder.b.tvRating.text = "★ ${d.rating}"
        Glide.with(context)
            .load(d.foto)
            .override(200, 200)
            .centerCrop()
            .into(holder.b.imgDokter)

        // Status ketersediaan
        if (d.tersedia) {
            holder.b.tvStatus.text = "● Tersedia"
            holder.b.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.teal_dark))
        } else {
            holder.b.tvStatus.text = "● Penuh"
            holder.b.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.coral))
        }

        // Klik kartu maupun tombol "Lihat Detail"
        holder.b.root.setOnClickListener { onClick(d) }
        holder.b.btnDetail.setOnClickListener { onClick(d) }

        Log.d("DokterAdapter", "Bind posisi $position: ${d.nama}")
    }

    override fun getItemCount() = list.size

    // Dipanggil saat chip atau pencarian berubah
    fun updateData(newList: List<Dokter>) {
        list = newList
        notifyDataSetChanged()
    }
}