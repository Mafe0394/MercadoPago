package com.projects.mercadopago.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projects.mercadopago.databinding.HolderProductImagesBinding

class ImageSliderAdapter(private val imageUrls: List<String>) :
    RecyclerView.Adapter<ImageSliderAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(private var binding: HolderProductImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) {
            binding.apply {
                this.imageUrl = imageUrl
                executePendingBindings()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        val binding = HolderProductImagesBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        return Pager2ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.bind(imageUrls[position])
    }

    override fun getItemCount(): Int = imageUrls.size

}

