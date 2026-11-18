package com.example.project_juwan.Rekam

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class RekamTabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RekamListFragment()
            else -> ObatFragment()
        }
    }
}