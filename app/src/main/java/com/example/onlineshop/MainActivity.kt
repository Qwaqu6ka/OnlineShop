package com.example.onlineshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.onlineshop.databinding.ActivityMainBinding
import com.example.onlineshop.mainfragments.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(binding.container.id, HomeFragment())
        }

        binding.tabLayout.addOnTabSelectedListener(object: OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val fragment = when (tab?.contentDescription) {
                    getString(R.string.home_page) -> HomeFragment()
                    getString(R.string.profile) -> ProfileFragment()
                    getString(R.string.favorites) -> FavoritesFragment()
                    getString(R.string.cart) -> CartFragment()
                    getString(R.string.chat) -> ChatFragment()
                    else -> null
                }
                fragment?.let {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace(binding.container.id, it)
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}