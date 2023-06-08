package com.example.trashcash.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trashcash.R
import com.example.trashcash.helper.addThousandSeparator
import com.example.trashcash.model.PostItem

class PostsAdapter(private val context: Context, private val posts: List<PostItem>): RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.card_post, viewGroup, false))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Glide.with(viewHolder.itemView.context)
            .load(posts[position].imageUrl)
            .into(viewHolder.imgPost)

        viewHolder.category.text = posts[position].category
        viewHolder.title.text = posts[position].category
        viewHolder.qty.text = "Qty: ${posts[position].quantity} Kg"
        viewHolder.price.text = "Mulai dari IDR ${addThousandSeparator(posts[position].price)}"

        viewHolder.detailBtn.setOnClickListener {  }
    }

    override fun getItemCount() = posts.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPost: ImageView = view.findViewById(R.id.iv_card_post)
        val category: TextView = view.findViewById(R.id.tv_badge_card_category)
        val title: TextView = view.findViewById(R.id.tv_card_title)
        val price: TextView = view.findViewById(R.id.tv_trash_price)
        val qty: TextView = view.findViewById(R.id.tv_trash_qty)
        val detailBtn: Button = view.findViewById(R.id.btn_see_detail)
    }
}