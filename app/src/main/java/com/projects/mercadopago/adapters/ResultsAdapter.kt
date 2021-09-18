package com.projects.mercadopago.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.databinding.HolderProductSearchBinding
import com.projects.mercadopago.util.ProductClick

class ResultsAdapter(private val callback: ProductClick) :
    PagingDataAdapter<Product, ResultsAdapter.HolderProductAdapter>(
        DiffCallback) {

    private lateinit var itemList: List<Product>

    fun submitList(items: List<Product>?) {
        itemList = items ?: ArrayList()
    }

    class HolderProductAdapter(private var binding: HolderProductSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, callback: ProductClick) {
            binding.also {
                it.product = product
                it.productCallback = callback
            }
            // Causes the update to excute immediately
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderProductAdapter {
        val binding = HolderProductSearchBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        return HolderProductAdapter(binding)
    }

    override fun onBindViewHolder(holder: HolderProductAdapter, position: Int) {
        holder.bind(itemList[position], callback)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.productID == newItem.productID
        }
    }

}