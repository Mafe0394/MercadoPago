package com.projects.mercadopago.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.databinding.HolderProductSearchBinding
import com.projects.mercadopago.databinding.HolderVisitedProductBinding
import com.projects.mercadopago.util.ProductClick

class VisitedProductsAdapter(private val onItemClick: ProductClick) :
    RecyclerView.Adapter<VisitedProductsAdapter.VisitedProductViewHolder>() {

    private lateinit var visitedProducts: List<Product>

    fun submitList(products: List<Product>?) {
        visitedProducts = products ?: ArrayList()
    }

    inner class VisitedProductViewHolder(private val binding: HolderVisitedProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, onClick: ProductClick) {
            binding.product = product
            // Causes the update to execute immediately
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitedProductViewHolder {
        val binding = HolderVisitedProductBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        return VisitedProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VisitedProductViewHolder, position: Int) {
        holder.bind(product = visitedProducts[position], onItemClick)
    }

    override fun getItemCount(): Int = visitedProducts.size
}