package com.projects.mercadopago.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.projects.mercadopago.databinding.HolderResultsRecyclerViewBinding
import com.projects.mercadopago.domain.ProductModel


class ResultsAdapter : ListAdapter<ProductModel,
        ResultsAdapter.ProductsViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ProductsViewHolder = ProductsViewHolder(
        HolderResultsRecyclerViewBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }


    companion object DiffCallback : DiffUtil.ItemCallback<ProductModel>() {
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ProductsViewHolder(private var binding: HolderResultsRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductModel) {
            binding.product = product
            // Causes the update to excute immediately
            binding.executePendingBindings()
        }

    }
}