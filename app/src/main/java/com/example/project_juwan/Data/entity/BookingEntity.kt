package com.example.project_juwan.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Locale

@Entity(tableName = "bookings")
data class BookingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val namaDokter: String,
    val spesialis: String,
    val keluhan: String,
    val tanggal: String,   // yyyy-MM-dd
    val jam: String,       // HH:mm
    val status: String = "Terjadwal"
) {
    // Contoh hasil: "Sabtu, 28 November 2026"
    fun tanggalLengkap(): String {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(tanggal) ?: return tanggal
        return SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID")).format(date)
    }
}