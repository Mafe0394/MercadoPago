package com.projects.mercadopago.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.databinding.HolderProductImagesBinding
import com.projects.mercadopago.databinding.HolderProductSearchBinding
import com.projects.mercadopago.util.ProductClick

class ProductPicturesAdapter:RecyclerView.Adapter<ProductPicturesAdapter.HolderImageAdapter>() {

    private lateinit var imageUrls:List<String>

    fun submitList(items:List<String>?){
        imageUrls=items?:ArrayList()
    }

    inner class HolderImageAdapter(private var binding: HolderProductImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String, visible:Boolean) {
            binding.also {
                it.imageUrl=imageUrl
                it.visible=visible
            }
            // Causes the update to excute immediately
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderImageAdapter {
        val binding = HolderProductImagesBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        return HolderImageAdapter(binding)
    }

    override fun onBindViewHolder(holder: HolderImageAdapter, position: Int) {
        holder.bind(imageUrls[position],true)
    }

    override fun getItemCount(): Int = imageUrls.size
}

class ImageSliderAdapter(private val imageUrls:List<String>):RecyclerView.Adapter<ImageSliderAdapter.Pager2ViewHolder>(){

    inner class Pager2ViewHolder(private var binding: HolderProductImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String, visible:Boolean) {
            binding.also {
                it.imageUrl=imageUrl
                it.visible=visible
            }
            // Causes the update to excute immediately
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        val binding = HolderProductImagesBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        return Pager2ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.bind(imageUrls[position],true)
    }

    override fun getItemCount(): Int = imageUrls.size

}

