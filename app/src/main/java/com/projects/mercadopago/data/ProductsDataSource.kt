package com.projects.mercadopago.data

import androidx.lifecycle.LiveData
import com.projects.mercadopago.data.database.DatabaseProduct
import com.projects.mercadopago.data.database.ProductsDatabase
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.domain.ResponseModel
import com.projects.mercadopago.data.repository.ResultMercadoPago

interface ProductsDataSource {
    fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>>

    suspend fun getProducts(): ResultMercadoPago<List<Product>>?

    suspend fun refreshProducts(query:String):ResultMercadoPago<ResponseModel>?

    fun observeProduct(ProductId: String): LiveData<ResultMercadoPago<Product>>

    suspend fun getProduct(ProductId: String): ResultMercadoPago<Product>

    suspend fun refreshProduct(ProductId: String)

    suspend fun saveProduct(Product: Product)

    suspend fun activateProduct(Product: Product)

    suspend fun activateProduct(ProductId: String)//maybe for favorite

    suspend fun deleteAllProducts()

    suspend fun deleteProduct(ProductId: String)

    suspend fun saveProductsList(productsList:List<DatabaseProduct>)
}