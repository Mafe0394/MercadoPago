package com.projects.mercadopago.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.projects.mercadopago.data.ProductsDataSource
import com.projects.mercadopago.data.database.ProductLocalDataSource
import com.projects.mercadopago.data.database.ProductsDatabase
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.domain.asDatabaseModel
import com.projects.mercadopago.data.domain.asDomainModel
import com.projects.mercadopago.data.network.MercadoPagoNetwork
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
    @MercadoPagoService val mercadoPagoNetwork: ProductsDataSource
) : IProductsRepository {

    /**
     * Refresh the products stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     */

//    companion object {
//        @Volatile
//        private var INSTANCE: ProductsRepository? = null
//
//        fun getRepository(appContext: Context): ProductsRepository {
//            return INSTANCE ?: synchronized(this) {
//                val database = Room.databaseBuilder(
//                    appContext,
//                    ProductsDatabase::class.java,
//                    "product_history_database"
//                ).fallbackToDestructiveMigration().build()
//                ProductsRepository(
//                    mercadoPagoLocal = ProductLocalDataSource(database.productDao),
//                    mercadoPagoNetwork = MercadoPagoNetwork
//                ).also {
//                    INSTANCE = it
//                }
//            }
//        }
//    }

    override suspend fun getProducts(query: String): ResultMercadoPago<List<Product>>? {
        try {
            withContext(Dispatchers.IO) {
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

    override suspend fun refreshProduct(productID: String) {
    }

    override suspend fun getProduct(
        productID: String,
    ): ResultMercadoPago<Product> = withContext(Dispatchers.IO) {
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
        withContext(Dispatchers.IO) {
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
        withContext(Dispatchers.IO) {
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