package com.example.project_juwan.Rekam

import com.example.project_juwan.R

object RekamData {
    val daftarRekam = listOf(
        RekamMedis("10 Agustus 2026", "Pemeriksaan rutin", "Dr. Andi Pratama", "Spesialis Umum", "Tekanan darah normal", R.drawable.ic_chat),
        RekamMedis("11 Agustus 2026", "Pemeriksaan gigi", "Dr. Budi Santoso", "Spesialis Gigi", "Gigi berkarang", R.drawable.ic_chat),
        RekamMedis("11 Agustus 2026", "Hasil laboratorium", "Dr. Andi Pratama", "Spesialis Umum", "Hasil tes darah", R.drawable.ic_description),
        RekamMedis("12 Agustus 2026", "Resep obat", "Dr. Siti Rahma", "Spesialis Anak", "Vitamin dan obat pereda demam", R.drawable.ic_description)
    )

    val daftarObat = listOf(
        Obat("Paracetamol 500 mg", "1 tablet", "3x sehari, sesudah makan", "Diminum saat demam, selama 3 hari"),
        Obat("Vitamin C 500 mg", "1 tablet", "1x sehari, pagi hari", "Diminum rutin selama 7 hari"),
        Obat("Obat kumur antiseptik", "10 ml", "2x sehari, pagi dan malam", "Berkumur 30 detik, jangan ditelan"),
        Obat("Amoxicillin 500 mg", "1 kapsul", "3x sehari, setiap 8 jam", "Wajib dihabiskan sesuai resep")
    )
}