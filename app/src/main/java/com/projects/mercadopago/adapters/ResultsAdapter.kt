package com.projects.mercadopago.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.projects.mercadopago.databinding.HolderResultsRecyclerViewBinding
import com.projects.mercadopago.domain.ResultModel


class ResultsAdapter : ListAdapter<ResultModel,
        ResultsAdapter.ProductsViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ProductsViewHolder = ProductsViewHolder(
        HolderResultsRecyclerViewBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }


    companion object DiffCallback : DiffUtil.ItemCallback<ResultModel>() {
        override fun areItemsTheSame(oldItem: ResultModel, newItem: ResultModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ResultModel, newItem: ResultModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ProductsViewHolder(private var binding: HolderResultsRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ResultModel) {
            binding.product = product
            // Causes the update to excute immediately
            binding.executePendingBindings()
        }

    }
}