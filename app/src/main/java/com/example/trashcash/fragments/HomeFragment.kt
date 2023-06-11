package com.example.trashcash.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trashcash.adapter.PostsAdapter
import com.example.trashcash.databinding.FragmentHomeBinding
import com.example.trashcash.helper.FirebaseUtils
import com.example.trashcash.helper.ProgressBarHandler
import com.example.trashcash.viewmodels.PostViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var progressBarHandler: ProgressBarHandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[PostViewModel::class.java]

        progressBarHandler = ProgressBarHandler(binding.pbProfile)
        progressBarHandler.show()

        showData(viewModel)

        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            binding.rvHomePosts.layoutManager = LinearLayoutManager(requireContext())
            binding.rvHomePosts.adapter = PostsAdapter(requireContext(), posts)
        }

        binding.searchViewHome.setOnClickListener {
            binding.searchViewHome.isIconified =
                false
        }

        binding.searchViewHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchViewHome.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText != "") {
                    viewModel.filterPostByTitle(newText as String)
                } else {
                    showData(viewModel)
                }

                return false
            }
        })
    }

    private fun showData(viewModel: PostViewModel){
        FirebaseUtils.getIdToken { idToken ->
            if (idToken != null) {
                viewModel.getPosts(idToken, null, null)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Terjadi kesalahan pada autentikasi 🙏",
                    Toast.LENGTH_SHORT
                ).show()
            }

            progressBarHandler.hide()
        }
    }
}