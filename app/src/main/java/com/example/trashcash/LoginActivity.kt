package com.example.trashcash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trashcash.databinding.ActivityLoginBinding
import com.example.trashcash.helper.ProgressBarHandler

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val progressBar = ProgressBarHandler(binding.pbLogin)
        progressBar.hide()

        binding.toRegisterButton.setOnClickListener {
            val toRegisterIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(toRegisterIntent)
        }
    }
}