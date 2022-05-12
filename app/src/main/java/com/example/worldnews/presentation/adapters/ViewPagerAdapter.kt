package com.example.worldnews.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldnews.databinding.ItemPageBinding

class ViewPagerAdapter(private val description: String?, private val image: String?) :
    RecyclerView.Adapter<ViewPagerAdapter.PagerVH>() {

    private val detailList = arrayListOf(description, image)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH {
        return PagerVH(ItemPageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = detailList.size

    override fun onBindViewHolder(holder: PagerVH, position: Int): Unit = holder.itemView.run {
        holder.tvFullDesc.text = detailList[position]
        holder.tvFullDesc.text.apply {
            if (this.startsWith("http")) holder.tvFullDesc.visibility = View.GONE
        }
        Glide.with(context).load(detailList[position]).into(holder.tvImage)
    }

    inner class PagerVH(private val binding: ItemPageBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvFullDesc = binding.fullDescription
        val tvImage = binding.image
    }
}