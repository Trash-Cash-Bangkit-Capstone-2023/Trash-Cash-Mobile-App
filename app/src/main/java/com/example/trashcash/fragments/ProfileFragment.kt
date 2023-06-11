package com.example.trashcash.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trashcash.EditProfileActivity
import com.example.trashcash.LoginActivity
import com.example.trashcash.adapter.PostsAdapter
import com.example.trashcash.adapter.ProfilePostsAdapter
import com.example.trashcash.databinding.FragmentProfileBinding
import com.example.trashcash.helper.FirebaseUtils
import com.example.trashcash.helper.ProgressBarHandler
import com.example.trashcash.model.User
import com.example.trashcash.viewmodels.UserViewModel
import com.google.firebase.auth.FirebaseAuth

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

        FirebaseUtils.getIdToken {idToken ->
            if(idToken != null){
                val auth = FirebaseAuth.getInstance()
                val currentUser = auth.currentUser
                val uid = currentUser?.uid

                viewModel.getUserPosts(idToken, uid as String)
            } else {
                Toast.makeText(requireContext(), "Terjadi kesalahan pada autentikasi 🙏", Toast.LENGTH_SHORT).show()
            }

            progressBarHandler.hide()
        }

        viewModel.posts.observe(viewLifecycleOwner){posts ->
            binding.rvProfilePosts.layoutManager = GridLayoutManager(requireContext(), 3)
            binding.rvProfilePosts.adapter = ProfilePostsAdapter(requireContext(), posts)
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