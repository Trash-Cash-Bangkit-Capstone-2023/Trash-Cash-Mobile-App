package com.example.trashcash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Toast
import com.example.trashcash.api.ApiConfig
import com.example.trashcash.databinding.ActivityRegisterBinding
import com.example.trashcash.helper.ProgressBarHandler
import com.example.trashcash.model.RegisterRequest
import com.example.trashcash.model.RegisterResponse
import com.jaredrummler.materialspinner.MaterialSpinner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val progressBar = ProgressBarHandler(binding.pbRegister)
        progressBar.hide()

        // Setup Province Spinner
        val provinceList = listOf(
            "Aceh",
            "Sumatera Utara",
            "Sumatera Barat",
            "Riau",
            "Kepulauan Riau",
            "Jambi",
            "Sumatera Selatan",
            "Bengkulu",
            "Lampung",
            "Kepulauan Bangka Belitung",
            "Jakarta",
            "Jawa Barat",
            "Jawa Tengah",
            "Yogyakarta",
            "Jawa Timur",
            "Bali",
            "Nusa Tenggara Barat",
            "Nusa Tenggara Timur",
            "Kalimantan Barat",
            "Kalimantan Tengah",
            "Kalimantan Selatan",
            "Kalimantan Timur",
            "Kalimantan Utara",
            "Sulawesi Utara",
            "Sulawesi Tengah",
            "Sulawesi Selatan",
            "Sulawesi Tenggara",
            "Sulawesi Barat",
            "Gorontalo",
            "Maluku",
            "Maluku Utara",
            "Papua Barat",
            "Papua"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, provinceList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner: MaterialSpinner = binding.spinnerProvince
        spinner.setAdapter(adapter)

        binding.registerButton.setOnClickListener {
            val username = binding.edRegisterName.text.toString()
            val email = binding.edRegisterEmail.text.toString()
            val password = binding.edRegisterPassword.text.toString()
            val address = binding.edRegisterAddress.text.toString()
            val phoneNumber = binding.edRegisterContact.text.toString()
            val province = binding.spinnerProvince.text.toString()

            if (username.isEmpty()) {
                binding.edRegisterName.error = "Name is required"
                binding.edRegisterName.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                binding.edRegisterEmail.error = "Email is required"
                binding.edRegisterEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.edRegisterPassword.error = "Password is required"
                binding.edRegisterPassword.requestFocus()
                return@setOnClickListener
            }

            if (address.isEmpty()){
                binding.edRegisterAddress.error = "Adress is required"
                binding.edRegisterAddress.requestFocus()
                return@setOnClickListener
            }

            if (phoneNumber.isEmpty()){
                binding.edRegisterContact.error = "Phone number is required"
                binding.edRegisterContact.requestFocus()
                return@setOnClickListener
            }

            if (province.isEmpty()){
                binding.spinnerProvince.error = "Please select your province"
                binding.spinnerProvince.requestFocus()
                return@setOnClickListener
            }

            handleRegister(this, username, email, password, address, phoneNumber, province)
        }

        binding.toLoginButton.setOnClickListener {
            val toLoginIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(toLoginIntent)
        }
    }

    private fun handleRegister(
        context: Context,
        username: String,
        email: String,
        password: String,
        address: String,
        phoneNumber: String,
        province: String
    ){
        val progressBar = ProgressBarHandler(binding.pbRegister)

        val client = ApiConfig.getApiService().registerUser(
            RegisterRequest(email, password, username, phoneNumber, province, address)
        )

        progressBar.show()

        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    Toast.makeText(context, registerResponse?.message, Toast.LENGTH_SHORT).show()

                    val toLoginIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(toLoginIntent)
                } else {
                    Toast.makeText(context, "Gagal melakukan registrasi", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                progressBar.hide()
                Toast.makeText(context, "Gagal melakukan registrasi", Toast.LENGTH_SHORT).show()
            }
        })
    }
}