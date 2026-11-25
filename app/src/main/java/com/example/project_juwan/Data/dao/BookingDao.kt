package com.example.project_juwan.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.project_juwan.data.entity.BookingEntity

@Dao
interface BookingDao {
    // Booking yang masih berjalan (tampil di Home), dari yang terdekat
    @Query("SELECT * FROM bookings WHERE status = 'Terjadwal' ORDER BY tanggal ASC, jam ASC")
    suspend fun getAktif(): List<BookingEntity>

    // Booking yang sudah selesai (tampil di Rekam Medis), dari yang terbaru
    @Query("SELECT * FROM bookings WHERE status = 'Selesai' ORDER BY tanggal DESC, jam DESC")
    suspend fun getRiwayat(): List<BookingEntity>

    @Insert
    suspend fun insert(booking: BookingEntity)

    @Update
    suspend fun update(booking: BookingEntity)

    @Delete
    suspend fun delete(booking: BookingEntity)
}