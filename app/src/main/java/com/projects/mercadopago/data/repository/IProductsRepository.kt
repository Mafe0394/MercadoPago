package com.projects.mercadopago.data.repository

import androidx.lifecycle.LiveData
import com.projects.mercadopago.data.domain.Product

interface IProductsRepository {

    suspend fun getProducts(query: String): ResultMercadoPago<List<Product>>?

    fun observeVisitedProducts():LiveData<ResultMercadoPago<List<Product>>>

    fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>>

    suspend fun refreshProduct(productID: String)

    suspend fun getVisitedProducts():ResultMercadoPago<List<Product>>?

    suspend fun getProduct(productID: String): ResultMercadoPago<Product>

    suspend fun deleteAllProducts()

    suspend fun getProductDescription(productID:String):ResultMercadoPago<String>

    suspend fun deleteVisitedProducts()
}