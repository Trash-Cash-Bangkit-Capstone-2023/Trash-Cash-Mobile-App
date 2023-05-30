package com.example.trashcash

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.trashcash.databinding.ActivityEditProfileBinding
import com.example.trashcash.helper.FirebaseUtils
import com.example.trashcash.model.EditRequest
import com.example.trashcash.model.User
import com.example.trashcash.viewmodels.UserViewModel

import com.jaredrummler.materialspinner.MaterialSpinner

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.white)))
            title = Html.fromHtml("<font color=\"black\">Edit Profile</font>")
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
            setDisplayHomeAsUpEnabled(true)
        }

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[UserViewModel::class.java]

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

        val spinner: MaterialSpinner = binding.spinnerEditProvince
        spinner.setAdapter(adapter)

        FirebaseUtils.getIdToken {idToken ->
            if(idToken != null){
                viewModel.getUserProfile(idToken)
            } else {
                Toast.makeText(this, "Terjadi kesalahan pada autentikasi 🙏", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.profile.observe(this){
            showUserData(it.user)
            val selectedPosition = adapter.getPosition(it.user.province)
            spinner.selectedIndex = selectedPosition
        }

        binding.editButton.setOnClickListener {
            val request = EditRequest(
                name = binding.edEditName.text.toString(),
                email = binding.edEditEmail.text.toString(),
                phone = binding.edEditContact.text.toString(),
                province = binding.spinnerEditProvince.text.toString(),
                address = binding.edEditAddress.text.toString()
            )

            FirebaseUtils.getIdToken {idToken ->
                if(idToken != null){
                    viewModel.editUserProfile(idToken, request)
                    Toast.makeText(this, "Edit profile berhasil 👍", Toast.LENGTH_SHORT).show()

                    val toMainIntent = Intent(this@EditProfileActivity, MainActivity::class.java)
                    startActivity(toMainIntent)
                } else {
                    Toast.makeText(this, "Terjadi kesalahan pada autentikasi 🙏", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showUserData(user: User){
        binding.edEditName.setText(user.name)
        binding.edEditEmail.setText(user.email)
        binding.edEditContact.setText(user.phone)
        binding.edEditAddress.setText(user.address)
    }
}