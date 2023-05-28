package com.example.trashcash.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.trashcash.databinding.FragmentProfileBinding
import com.example.trashcash.helper.FirebaseUtils
import com.example.trashcash.helper.ProgressBarHandler
import com.example.trashcash.model.User
import com.example.trashcash.viewmodels.MainViewModel

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

        val viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]

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
    }

    private fun showProfileData(user: User){
        binding.tvName.text = user.name
        binding.tvPhone.text = user.phone
        binding.tvAdress.text = user.address
    }
}