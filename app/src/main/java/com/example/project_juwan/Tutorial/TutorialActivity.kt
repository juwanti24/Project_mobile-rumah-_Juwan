package com.example.project_juwan

import android.animation.ArgbEvaluator
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.project_juwan.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        val fragments = listOf(Tutorial1Fragment(), Tutorial2Fragment(), Tutorial3Fragment())
        binding.viewPagerTutorial.adapter = TutorialFragmentAdapter(this, fragments)
        binding.dotIndicator.attachTo(binding.viewPagerTutorial)

        val warnaLatar = listOf(
            ContextCompat.getColor(this, R.color.pale_blue),
            ContextCompat.getColor(this, R.color.coral_light),
            ContextCompat.getColor(this, R.color.mint_teal)
        )
        binding.root.setBackgroundColor(warnaLatar[0])

        binding.viewPagerTutorial.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, offset: Float, offsetPx: Int) {
                val berikut = minOf(position + 1, warnaLatar.size - 1)
                val warna = ArgbEvaluator().evaluate(offset, warnaLatar[position], warnaLatar[berikut]) as Int
                binding.root.setBackgroundColor(warna)
            }

            override fun onPageSelected(position: Int) {
                binding.btnMulai.text = if (position == fragments.size - 1) "Mulai" else "Lewati"
            }
        })

        binding.btnMulai.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}