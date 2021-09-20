package com.projects.mercadopago.data

import androidx.lifecycle.LiveData
import com.projects.mercadopago.data.database.DatabaseProduct
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.network.networkModels.ProductDetailsResponse
import com.projects.mercadopago.data.network.networkModels.ResponseModel
import com.projects.mercadopago.data.repository.ResultMercadoPago

interface ProductsDataSource {
    fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>>

    suspend fun getProducts(): ResultMercadoPago<List<Product>>?

    suspend fun refreshProducts(query:String):ResultMercadoPago<ResponseModel>?

    fun observeVisitedProducts(): LiveData<ResultMercadoPago<List<Product>>>

    suspend fun getProduct(productID: String): ResultMercadoPago<Product>?

    suspend fun refreshProduct(productID: String):ResultMercadoPago<ProductDetailsResponse>?

    suspend fun saveProduct(product: DatabaseProduct)

    suspend fun activateProduct(product: Product)

    suspend fun activateProduct(productID: String)//maybe for favorite

    suspend fun deleteAllProducts()

    suspend fun deleteProduct(productID: String)

    suspend fun saveProductsList(productsList:List<DatabaseProduct>)

    suspend fun getProductDescription(productID:String):ResultMercadoPago<String>?

    suspend fun getVisitedProducts():ResultMercadoPago<List<Product>>?

    suspend fun deleteVisitedProducts()
}