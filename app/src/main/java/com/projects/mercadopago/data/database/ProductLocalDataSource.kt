package com.projects.mercadopago.data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.projects.mercadopago.data.ProductsDataSource
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.domain.ResponseModel
import com.projects.mercadopago.data.domain.asDomainModel
import com.projects.mercadopago.data.repository.ResultMercadoPago
import com.projects.mercadopago.data.repository.ResultMercadoPago.Success
import com.projects.mercadopago.data.repository.ResultMercadoPago.Error
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

    override suspend fun activateProduct(Product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun activateProduct(ProductId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllProducts() = withContext(ioDispatcher) {
        productDao.deleteProducts()
    }

    override suspend fun deleteProduct(ProductId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveProductsList(productsList: List<DatabaseProduct>) {
        productDao.insertListOfProducts(productsList)
    }


}