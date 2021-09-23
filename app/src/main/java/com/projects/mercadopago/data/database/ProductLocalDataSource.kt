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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.annotation.meta.When
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Concrete implementation of a data source as a db.
 */
@Singleton
class ProductLocalDataSource  @Inject internal constructor(
    private val productDao: ProductDao,
) : ProductsDataSource {

    override fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>> =
        productDao.observeProducts().map {
            Success(it.asDomainModel())
        }

    override suspend fun getProducts(): ResultMercadoPago<List<Product>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Success(productDao.getProductsFromDatabase().asDomainModel())
            } catch (e: Exception) {
                Error(e)
            }
        }

    override suspend fun refreshProducts(query: String): ResultMercadoPago<ResponseModel>? {
        TODO("Not implemented")
    }

    override fun observeVisitedProducts(): LiveData<ResultMercadoPago<List<Product>>> =
        productDao.observeVisitedProducts().map {
            Success(it.asDomainModel())
        }

    override suspend fun refreshProduct(productID: String): ResultMercadoPago<ProductDetailsResponse>? {
        TODO("Not implemented")
    }

    override suspend fun saveProduct(product: DatabaseProduct) {
        productDao.insertProduct(product = product)
    }

    override suspend fun deleteAllProducts() = withContext(Dispatchers.IO) {
        productDao.deleteProducts()
    }

    override suspend fun saveProductsList(productsList: List<DatabaseProduct>) {
        productDao.insertListOfProducts(productsList)
    }

    override suspend fun getProductDescription(productID: String): ResultMercadoPago<String>? {
        TODO("Not implemented")
    }

    override suspend fun getVisitedProducts(): ResultMercadoPago<List<Product>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Success(productDao.getVisitedProducts().asDomainModel())
            } catch (e: Exception) {
                Error(e)
            }
        }

    override suspend fun deleteVisitedProducts() = withContext(Dispatchers.IO) {
        productDao.deleteVisitedProducts()
    }

}