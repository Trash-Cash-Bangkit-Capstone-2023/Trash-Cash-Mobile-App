package com.example.trashcash.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trashcash.PostDetailActivity
import com.example.trashcash.R
import com.example.trashcash.model.UserPostItem

class ProfilePostsAdapter(private val context: Context, private val posts: List<UserPostItem>) :
    RecyclerView.Adapter<ProfilePostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.profile_card_post, viewGroup, false)
        )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Glide.with(viewHolder.itemView.context)
            .load(posts[position].imageUrl)
            .into(viewHolder.imgPost)

        viewHolder.imgPost.setOnClickListener {
            val toDetailIntent = Intent(context, PostDetailActivity::class.java)
            toDetailIntent.putExtra(PostDetailActivity.EXTRA_ID, posts[position].id)
            ContextCompat.startActivity(context, toDetailIntent, null)
        }
    }

    override fun getItemCount() = posts.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPost: ImageView = view.findViewById(R.id.iv_profile_post)
    }
}