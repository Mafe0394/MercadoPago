package com.projects.mercadopago.data.repository

import androidx.lifecycle.LiveData
import com.projects.mercadopago.data.ProductsDataSource
import com.projects.mercadopago.data.database.ProductsDatabase
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.domain.asDatabaseModel
import com.projects.mercadopago.data.domain.asDomainModel
import com.projects.mercadopago.data.repository.ResultMercadoPago.Success
import com.projects.mercadopago.di.MercadoPagoLocal
import com.projects.mercadopago.di.MercadoPagoService
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

/** [mercadoPagoLocal] is a [ProductsDatabase] object that works as the class's constructor
 * parameter to access the Dao methods
 * */
@Singleton
class ProductsRepository @Inject constructor(
    @MercadoPagoLocal val mercadoPagoLocal: ProductsDataSource,
    @MercadoPagoService val mercadoPagoNetwork: ProductsDataSource,
    private val ioDispatcher:CoroutineDispatcher=Dispatchers.IO
) : IProductsRepository {

    override suspend fun getProducts(query: String): ResultMercadoPago<List<Product>>? {
        try {
            withContext(ioDispatcher) {
                getSearchProducts(query)
            }
        } catch (error: Exception) {
            return ResultMercadoPago.Error(error)
        }

        return mercadoPagoLocal.getProducts()
    }

    private suspend fun getSearchProducts(query: String) {
        val remoteProducts = mercadoPagoNetwork.refreshProducts(query)
        if (remoteProducts is Success) {
            mercadoPagoLocal.saveProductsList(remoteProducts.data.asDatabaseModel())
        } else if (remoteProducts is ResultMercadoPago.Error) {
            throw remoteProducts.exception
        }
    }

    override fun observeVisitedProducts(): LiveData<ResultMercadoPago<List<Product>>> =
        mercadoPagoLocal.observeVisitedProducts()

    override fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>> =
        mercadoPagoLocal.observeProducts()

    override suspend fun refreshProducts() {
    }

    override suspend fun getProduct(
        productID: String,
    ): ResultMercadoPago<Product> = withContext(ioDispatcher) {
        try {
            val product = mercadoPagoNetwork.refreshProduct(productID)
            if (product is Success) {
                mercadoPagoLocal.saveProduct(product.data.asDatabaseModel())
                return@withContext Success(product.data.asDomainModel())
            } else {
                return@withContext ResultMercadoPago.Error((product as ResultMercadoPago.Error).exception)
            }
        } catch (e: Exception) {
            return@withContext ResultMercadoPago.Error(e)
        }
    }

    override suspend fun getProductDescription(productID: String): ResultMercadoPago<String> =
        withContext(ioDispatcher) {
            try {
                val description = mercadoPagoNetwork.getProductDescription(productID)
                if (description is Success)
                    return@withContext description
                else
                    return@withContext ResultMercadoPago.Error((description as ResultMercadoPago.Error).exception)
            } catch (e: Exception) {
                return@withContext ResultMercadoPago.Error(e)
            }

        }

    override suspend fun deleteAllProducts() {
        withContext(ioDispatcher) {
            coroutineScope {
                launch { mercadoPagoLocal.deleteAllProducts() }
            }
        }
    }

    override suspend fun getVisitedProducts(): ResultMercadoPago<List<Product>>? {
        return mercadoPagoLocal.getVisitedProducts()
    }

    override suspend fun deleteVisitedProducts() {
        mercadoPagoLocal.deleteVisitedProducts()
    }
}