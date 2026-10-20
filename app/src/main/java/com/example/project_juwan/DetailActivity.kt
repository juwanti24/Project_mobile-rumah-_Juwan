package com.example.project_juwan

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_juwan.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Wajib login
        val prefs = getSharedPreferences("session", MODE_PRIVATE)
        if (!prefs.getBoolean("isLogin", false)) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Detail Rekam Medis"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Data dari daftar Rekam Medis: "tanggal\njudul - dokter\ncatatan"
        val data = intent.getStringExtra("judul")
        if (data != null) {
            val baris = data.split("\n")
            val baris2 = baris.getOrElse(1) { "" }

            binding.tvTanggal.text = baris.getOrElse(0) { "" }
            binding.tvJudul.text = baris2.substringBefore(" - ")
            binding.tvDokter.text = baris2.substringAfter(" - ")
            binding.tvCatatan.text = baris.getOrElse(2) { "-" }
        }

        binding.BtnKembali.setOnClickListener { finish() }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}