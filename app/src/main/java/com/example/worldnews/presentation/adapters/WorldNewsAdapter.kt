package com.example.worldnews.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldnews.data.Results
import com.example.worldnews.databinding.WorldNewsItemBinding
import com.example.worldnews.presentation.WorldNewsFragment

class WorldNewsAdapter(private val context: WorldNewsFragment) : RecyclerView.Adapter<WorldNewsAdapter.WorldNewsViewHolder>() {

    var worldNewsList: List<Results> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onWorldNewsClickListener: OnWorldNewsClickListener? = null

    inner class WorldNewsViewHolder(private val binding: WorldNewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvTitle = binding.title
        val tvDescription = binding.description
        val tvImage = binding.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorldNewsViewHolder {
        return WorldNewsViewHolder(WorldNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: WorldNewsAdapter.WorldNewsViewHolder, position: Int) {
        val result = worldNewsList[position]
        with(holder) {
            with(result) {
                tvTitle.text = title
                tvDescription.text = description
                itemView.setOnClickListener {
                    onWorldNewsClickListener?.onWorldNewsClick(this)
                }
                Glide.with(context).load(imageUrl).into(tvImage)
            }
        }
    }

    override fun getItemCount() = worldNewsList.size

    interface OnWorldNewsClickListener {
        fun onWorldNewsClick (results: Results)
    }

}