package com.projects.mercadopago.data.database

import androidx.lifecycle.LiveData
import com.projects.mercadopago.data.ProductsDataSource
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.repository.ResultMercadoPago
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Concrete implementation of a data source as a db.
 */
class ProductLocalDataSource internal constructor(
    private val productDao:ProductDao,
    private val ioDispatcher: CoroutineDispatcher=Dispatchers.IO
):ProductsDataSource{
    override fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getProducts(): ResultMercadoPago<List<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshProducts() {
        TODO("Not yet implemented")
    }

    override fun observeProduct(ProductId: String): LiveData<ResultMercadoPago<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun getProduct(ProductId: String): ResultMercadoPago<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshProduct(ProductId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveProduct(Product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun completeProduct(Product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun completeProduct(ProductId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun activateProduct(Product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun activateProduct(ProductId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun clearCompletedProducts() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllProducts() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProduct(ProductId: String) {
        TODO("Not yet implemented")
    }


}