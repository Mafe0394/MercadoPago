package com.projects.mercadopago.repository

import androidx.lifecycle.LiveData
import com.projects.mercadopago.domain.Product

interface ProductsRepositoryInterface {
    suspend fun getProducts(forceUpdate: Boolean = false): ResultMercadoPago<List<Product>>

    suspend fun refreshProducts()
    fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>>

    suspend fun refreshProduct(ProductId: String)
    fun observeProduct(ProductId: String): LiveData<ResultMercadoPago<Product>>

    /**
     * Relies on [getProducts] to fetch data and picks the Product with the same ID.
     */
    suspend fun getProduct(ProductId: String, forceUpdate: Boolean = false): ResultMercadoPago<Product>

    suspend fun saveProduct(Product: Product)

    suspend fun completeProduct(Product: Product)

    suspend fun completeProduct(ProductId: String)

    suspend fun activateProduct(Product: Product)

    suspend fun activateProduct(ProductId: String)

    suspend fun clearCompletedProducts()

    suspend fun deleteAllProducts()

    suspend fun deleteProduct(ProductId: String)
}