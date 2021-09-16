package com.projects.mercadopago.data

import androidx.lifecycle.LiveData
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.repository.ResultMercadoPago

interface ProductsDataSource {
    fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>>

    suspend fun getProducts(): ResultMercadoPago<List<Product>>

    suspend fun refreshProducts()

    fun observeProduct(ProductId: String): LiveData<ResultMercadoPago<Product>>

    suspend fun getProduct(ProductId: String): ResultMercadoPago<Product>

    suspend fun refreshProduct(ProductId: String)

    suspend fun saveProduct(Product: Product)

    suspend fun completeProduct(Product: Product)

    suspend fun completeProduct(ProductId: String)

    suspend fun activateProduct(Product: Product)

    suspend fun activateProduct(ProductId: String)

    suspend fun clearCompletedProducts()

    suspend fun deleteAllProducts()

    suspend fun deleteProduct(ProductId: String)
}