package com.projects.mercadopago.data.repository

import androidx.lifecycle.LiveData
import com.projects.mercadopago.data.domain.Product

interface IProductsRepository {

    suspend fun getProducts(query: String): ResultMercadoPago<List<Product>>?

    fun observeVisitedProducts():LiveData<ResultMercadoPago<List<Product>>>

    fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>>

    suspend fun refreshProduct(productID: String)

    fun observeProduct(productID: String): LiveData<ResultMercadoPago<Product>>

    suspend fun getVisitedProducts():ResultMercadoPago<List<Product>>?

    /**
     * Relies on [getProducts] to fetch data and picks the Product with the same ID.
     */
    suspend fun getProduct(productID: String): ResultMercadoPago<Product>

    suspend fun saveProduct(product: Product)

    suspend fun completeProduct(product: Product)

    suspend fun completeProduct(productID: String)

    suspend fun activateProduct(product: Product)

    suspend fun activateProduct(productID: String)

    suspend fun clearCompletedProducts()

    suspend fun deleteAllProducts()

    suspend fun deleteProduct(productID: String)

    suspend fun getProductDescription(productID:String):ResultMercadoPago<String>
}