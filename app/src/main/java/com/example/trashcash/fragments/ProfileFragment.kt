package com.example.trashcash.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.trashcash.EditProfileActivity
import com.example.trashcash.LoginActivity
import com.example.trashcash.databinding.FragmentProfileBinding
import com.example.trashcash.helper.FirebaseUtils
import com.example.trashcash.helper.ProgressBarHandler
import com.example.trashcash.model.User
import com.example.trashcash.viewmodels.UserViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var progressBarHandler: ProgressBarHandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())[UserViewModel::class.java]

        progressBarHandler = ProgressBarHandler(binding.pbProfile)
        progressBarHandler.show()

        FirebaseUtils.getIdToken {idToken ->
            if(idToken != null){
                viewModel.getUserProfile(idToken)
            } else {
                Toast.makeText(requireContext(), "Terjadi kesalahan pada autentikasi 🙏", Toast.LENGTH_SHORT).show()
            }

            progressBarHandler.hide()
        }

        viewModel.profile.observe(requireActivity()){
            showProfileData(it.user)
        }

        binding.btnLogout.setOnClickListener{
            FirebaseUtils.logout()

            val toLoginIntent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(toLoginIntent)
        }

        binding.btnEditProfile.setOnClickListener {
            val toEditProfileIntent = Intent(requireActivity(), EditProfileActivity::class.java)
            startActivity(toEditProfileIntent)
        }
    }

    private fun showProfileData(user: User){
        binding.tvName.text = user.name
        binding.tvPhone.text = user.phone
        binding.tvAdress.text = user.address
    }
}