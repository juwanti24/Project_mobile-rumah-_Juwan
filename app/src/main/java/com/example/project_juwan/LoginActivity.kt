package com.example.project_juwan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_juwan.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val validEmail = "admin@sehatku.com"
        val validPassword = "123456"

        binding.BtnLogin.setOnClickListener {
            val email = binding.EdtEmail.text.toString().trim()
            val pass = binding.EdtPassword.text.toString()

            // 1. Cek kosong
            if (email.isEmpty()) {
                binding.tilEmail.error = "Email tidak boleh kosong"
                return@setOnClickListener
            }
            binding.tilEmail.error = null

            if (pass.isEmpty()) {
                binding.tilPassword.error = "Kata sandi tidak boleh kosong"
                return@setOnClickListener
            }
            binding.tilPassword.error = null

            if (email == validEmail && pass == validPassword) {
                val prefs = getSharedPreferences("session", MODE_PRIVATE)
                prefs.edit()
                    .putBoolean("isLogin", true)
                    .putString("email", email)
                    .apply()

                Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, BaseActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Email atau kata sandi salah", Toast.LENGTH_SHORT).show()
            }
        }
}
}