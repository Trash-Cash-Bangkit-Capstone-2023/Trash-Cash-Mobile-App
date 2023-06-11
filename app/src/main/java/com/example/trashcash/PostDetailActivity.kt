package com.example.trashcash

import android.R.attr.phoneNumber
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.trashcash.databinding.ActivityPostDetailBinding
import com.example.trashcash.helper.FirebaseUtils
import com.example.trashcash.helper.ProgressBarHandler
import com.example.trashcash.helper.addThousandSeparator
import com.example.trashcash.viewmodels.PostViewModel


class PostDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostDetailBinding
    private lateinit var progressBarHandler: ProgressBarHandler

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.white)))
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
            setDisplayHomeAsUpEnabled(true)
        }

        progressBarHandler = ProgressBarHandler(binding.pbDetailPost)

        val postId = intent.getStringExtra(EXTRA_ID)
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[PostViewModel::class.java]

        progressBarHandler.show()

        FirebaseUtils.getIdToken { idToken ->
            if (idToken != null) {
                viewModel.getPost(idToken, postId as String)
            } else {
                Toast.makeText(this, "Terjadi kesalahan pada autentikasi 🙏", Toast.LENGTH_SHORT)
                    .show()
            }

            progressBarHandler.hide()
        }

        viewModel.post.observe(this) { post ->
            supportActionBar?.title = Html.fromHtml("<font color=\"black\">${post.title}</font>")

            Glide.with(this)
                .load(post.imageUrl)
                .into(binding.ivPostDetail)

            binding.apply {
                tvDetailPostTitle.text = post.title
                tvPostDetailCategory.text = post.category
                tvDetailPostUsername.text = post.user.name
                tvDetailPostPrice.text =
                    getString(R.string.tv_price, addThousandSeparator(post.price.toLong()))
                tvDetailPostQty.text = getString(R.string.tv_qty, post.quantity)
                tvProvince.text = post.user.province
                tvPostDetailDescription.text = post.description


                btnContactToBuy.setOnClickListener {
                    val phoneUri: Uri = Uri.parse("tel:${post.user.phone}")
                    val phoneIntent = Intent(Intent.ACTION_DIAL)
                    phoneIntent.data = phoneUri

                    startActivity(phoneIntent)
                }
            }
        }
    }
}