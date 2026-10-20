package com.example.project_juwan

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.project_juwan.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mulaiAnimasi()

        Handler(Looper.getMainLooper()).postDelayed({
            val prefs = getSharedPreferences("session", MODE_PRIVATE)
            val isLogin = prefs.getBoolean("isLogin", false)

            val tujuan = if (isLogin) BaseActivity::class.java
            else LoginActivity::class.java

            startActivity(Intent(this, tujuan))
            finish()
        }, 2500)
    }

    private fun mulaiAnimasi() {
        // Posisi awal: transparan dan sedikit mengecil
        binding.imgLogo.alpha = 0f
        binding.imgLogo.scaleX = 0.7f
        binding.imgLogo.scaleY = 0.7f
        binding.tvAppName.alpha = 0f
        binding.tvTagline.alpha = 0f
        binding.loading.alpha = 0f

        // Logo: fade-in + membesar
        binding.imgLogo.animate()
            .alpha(1f).scaleX(1f).scaleY(1f)
            .setDuration(800)
            .start()

        // Teks muncul bergantian
        binding.tvAppName.animate().alpha(1f).setDuration(600).setStartDelay(500).start()
        binding.tvTagline.animate().alpha(1f).setDuration(600).setStartDelay(800).start()

        // Loading muncul terakhir
        binding.loading.animate().alpha(1f).setDuration(400).setStartDelay(1100).start()
    }
}