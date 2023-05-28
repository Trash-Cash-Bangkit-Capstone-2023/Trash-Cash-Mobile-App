package com.example.trashcash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.trashcash.databinding.ActivityMainBinding
import com.example.trashcash.fragments.HomeFragment
import com.example.trashcash.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home_nav_item -> replaceFragment(HomeFragment())
                R.id.profile_nav_item -> replaceFragment(ProfileFragment())
                else -> {}
            }

            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainFrameLayout, fragment)
        fragmentTransaction.commit()
    }
}