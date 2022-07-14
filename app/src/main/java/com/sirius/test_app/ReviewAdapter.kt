package com.sirius.test_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView

class ReviewAdapter(
    private val layoutInflater: LayoutInflater,
    private val reviews: List<ReviewModel>
) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_review, parent, false)
        return ReviewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviews[position])
    }

    override fun getItemCount() = reviews.size

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivPhoto: ImageView = itemView.findViewById(R.id.ivPhoto)
        private val tvName: MaterialTextView = itemView.findViewById(R.id.tvName)
        private val tvDate: MaterialTextView = itemView.findViewById(R.id.tvDate)
        private val tvMessage: MaterialTextView = itemView.findViewById(R.id.tvMessage)

        fun bind(review: ReviewModel) {
            Glide.with(itemView.context)
                .load(review.userImage)
                .circleCrop()
                .into(ivPhoto)

            tvName.text = review.userName
            tvDate.text = review.date
            tvMessage.text = review.message
        }
    }
}