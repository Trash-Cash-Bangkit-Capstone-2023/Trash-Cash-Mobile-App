package com.example.trashcash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.trashcash.databinding.ActivityLoginBinding
import com.example.trashcash.helper.ProgressBarHandler
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBarHandler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        progressBar = ProgressBarHandler(binding.pbLogin)
        progressBar.hide()

        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            val toMainIntent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(toMainIntent)
        }

        binding.loginButton.setOnClickListener {
            val email = binding.edLoginEmail.text.toString()
            val password = binding.edLoginPassword.text.toString()

            if (email.isEmpty()) {
                binding.edLoginEmail.error = "Email is required"
                binding.edLoginEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.edLoginPassword.error = "Password is required"
                binding.edLoginPassword.requestFocus()
                return@setOnClickListener
            }


            login(email, password)
        }

        binding.toRegisterButton.setOnClickListener {
            val toRegisterIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(toRegisterIntent)
        }
    }

    private fun login(email: String, password: String) {
        progressBar.show()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val toMainIntent = Intent(this@LoginActivity, MainActivity::class.java)
                    Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
                    startActivity(toMainIntent)
                } else {
                    Toast.makeText(this, "Login gagal: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

}