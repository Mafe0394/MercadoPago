package com.projects.mercadopago.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.projects.mercadopago.databinding.HolderResultsRecyclerViewBinding
import com.projects.mercadopago.domain.Product


class ResultsAdapter : ListAdapter<Product,
        ResultsAdapter.ProductsViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ProductsViewHolder = ProductsViewHolder(
        HolderResultsRecyclerViewBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.productID == newItem.productID
        }
    }

    class ProductsViewHolder(private var binding: HolderResultsRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.product = product
            // Causes the update to excute immediately
            binding.executePendingBindings()
        }

    }
}