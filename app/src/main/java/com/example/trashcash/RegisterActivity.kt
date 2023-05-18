package com.example.trashcash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.trashcash.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val spinnerItems = listOf<String>("Denpasar", "Buleleng", "Tabanan")


        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerCity.adapter = adapter

        binding.spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = spinnerItems[position]
                // Lakukan sesuatu dengan pilihan yang dipilih
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Tangani jika tidak ada yang dipilih
            }
        }
    }
}