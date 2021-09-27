package com.projects.mercadopago.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.projects.mercadopago.data.domain.Product

interface IProductsRepository {

    suspend fun getProducts(query: String): ResultMercadoPago<List<Product>>?

    fun observeVisitedProducts():LiveData<ResultMercadoPago<List<Product>>>

    fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>>

    suspend fun refreshProducts()

    suspend fun getVisitedProducts():ResultMercadoPago<List<Product>>?

    suspend fun getProduct(productID: String): ResultMercadoPago<Product>

    suspend fun deleteAllProducts()

    suspend fun getProductDescription(productID:String):ResultMercadoPago<String>

    suspend fun deleteVisitedProducts()

    suspend fun getProductsPaging(value: String?): LiveData<PagingData<Product>>
}