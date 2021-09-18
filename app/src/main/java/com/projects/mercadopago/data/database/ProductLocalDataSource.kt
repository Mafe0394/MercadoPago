package com.projects.mercadopago.data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.projects.mercadopago.data.ProductsDataSource
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.domain.asDomainModel
import com.projects.mercadopago.data.network.networkModels.ProductDetailsResponse
import com.projects.mercadopago.data.network.networkModels.ResponseModel
import com.projects.mercadopago.data.repository.ResultMercadoPago
import com.projects.mercadopago.data.repository.ResultMercadoPago.Error
import com.projects.mercadopago.data.repository.ResultMercadoPago.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Concrete implementation of a data source as a db.
 */
class ProductLocalDataSource internal constructor(
    private val productDao: ProductDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ProductsDataSource {
    override fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>> {
        return productDao.observeProducts().map {
            Success(it.asDomainModel())
        }
    }

    override suspend fun getProducts(): ResultMercadoPago<List<Product>> =
        withContext(ioDispatcher) {
            return@withContext try {
                Success(productDao.getProductsFromDatabase().asDomainModel())
            } catch (e: Exception) {
                Error(e)
            }
        }

    override suspend fun refreshProducts(query: String): ResultMercadoPago<ResponseModel>? {
        // no used
        return null
    }

    override fun observeProduct(productID: String): LiveData<ResultMercadoPago<Product>>? {
        return null
    }

    override suspend fun getProduct(productID: String): ResultMercadoPago<Product>? {
        return null
    }

    override suspend fun refreshProduct(productID: String): ResultMercadoPago<ProductDetailsResponse>? {
        return null
    }

    override suspend fun saveProduct(product: DatabaseProduct) {
        //nope
    }

    override suspend fun activateProduct(product: Product) {
        // nope
    }

    override suspend fun activateProduct(productID: String) {
        // nope
    }

    override suspend fun deleteAllProducts() = withContext(ioDispatcher) {
        productDao.deleteProducts()
    }

    override suspend fun deleteProduct(productID: String) {
        // nope
    }

    override suspend fun saveProductsList(productsList: List<DatabaseProduct>) {
        productDao.insertListOfProducts(productsList)
    }

    override suspend fun getProductDescription(productID: String): ResultMercadoPago<String> {
        TODO("Not yet implemented")
    }

}