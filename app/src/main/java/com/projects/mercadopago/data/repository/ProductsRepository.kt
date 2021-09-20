package com.projects.mercadopago.data.repository

import android.app.Application
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
import kotlinx.coroutines.*

/** [database] is a [ProductsDatabase] object that works as the class's constructor
 * parameter to access the Dao methods
 * */
class ProductsRepository(
    private val database: ProductsDataSource,
    private val mercadoPagoNetwork: ProductsDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : IProductsRepository {

    /**
     * Refresh the products stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     */

    companion object {
        @Volatile
        private var INSTANCE: ProductsRepository? = null

        fun getRepository(app: Application): ProductsRepository {
            return INSTANCE ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    app,
                    ProductsDatabase::class.java,
                    "product_history_database"
                ).fallbackToDestructiveMigration().build()
                ProductsRepository(
                    database = ProductLocalDataSource(database.productDao),
                    mercadoPagoNetwork = MercadoPagoNetwork
                ).also {
                    INSTANCE = it
                }
            }
        }
    }

    override suspend fun getProducts(query: String): ResultMercadoPago<List<Product>>? {
        try {
            withContext(ioDispatcher) {
                getSearchProducts(query)
            }
        } catch (error: Exception) {
            return ResultMercadoPago.Error(error)
        }

        return database.getProducts()
    }

    private suspend fun getSearchProducts(query: String) {
        val remoteProducts = mercadoPagoNetwork.refreshProducts(query)
        if (remoteProducts is Success) {
            database.saveProductsList(remoteProducts.data.asDatabaseModel())
        } else if (remoteProducts is ResultMercadoPago.Error) {
            throw remoteProducts.exception
        }
    }

    override fun observeVisitedProducts(): LiveData<ResultMercadoPago<List<Product>>> =
        database.observeVisitedProducts()

    override fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>> =
        database.observeProducts()

    override suspend fun refreshProduct(productID: String) {
    }

    override fun observeProduct(productID: String): LiveData<ResultMercadoPago<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun getProduct(
        productID: String,
    ): ResultMercadoPago<Product> = withContext(ioDispatcher) {
        try {
            val product = mercadoPagoNetwork.refreshProduct(productID)
            if (product is Success) {
                database.saveProduct(product.data.asDatabaseModel())
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
                val description=mercadoPagoNetwork.getProductDescription(productID)
                if (description is Success)
                    return@withContext description
                else
                    return@withContext ResultMercadoPago.Error((description as ResultMercadoPago.Error).exception)
            }catch (e:Exception){
                return@withContext ResultMercadoPago.Error(e)
            }

        }

    override suspend fun saveProduct(product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun completeProduct(product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun completeProduct(productID: String) {
        TODO("Not yet implemented")
    }

    override suspend fun activateProduct(product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun activateProduct(productID: String) {
        TODO("Not yet implemented")
    }

    override suspend fun clearCompletedProducts() {
        TODO("No implemented")
    }

    override suspend fun deleteAllProducts() {
        withContext(ioDispatcher) {
            coroutineScope {
                launch { database.deleteAllProducts() }
            }
        }
    }

    override suspend fun deleteProduct(productID: String) {
        database.deleteAllProducts()
    }

    override suspend fun getVisitedProducts(): ResultMercadoPago<List<Product>>? {
        return database.getVisitedProducts()
    }

    override suspend fun deleteVisitedProducts() {
        database.deleteVisitedProducts()
    }
}