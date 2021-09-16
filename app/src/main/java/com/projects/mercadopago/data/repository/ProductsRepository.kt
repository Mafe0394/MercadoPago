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

    //    val responseModel:LiveData<ResponseModel> = _responseModel
    // Transformations.map uses a conversion function to convert one LiveData object into
    // another LiveData object. it only is calculated when an activity or fragment
    // is observing the returned LiveData property
//    val products: LiveData<List<Product>> =
//        Transformations.map(database.productDao.getVisitedProducts()) {
//            it.asDomainModel()
//        }


    /**
     * Refresh the products stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     */
//    suspend fun getProductsQuery1(query: String) {
//        withContext(ioDispatcher) {
//            val products = mercadoPagoNetwork.getProductsByQuery(query = query)
//            // Store data in the database
//            database.productDao.insertListOfProducts(products = products.asDatabaseModel())
//        }
//    }
//
//    suspend fun deleteSearch() {
//        withContext(ioDispatcher) {
//            database.productDao.deleteProducts()
//        }
//    }

//    suspend fun getMoreProducts(
//        query: String,
//        offset: Double,
//        limit: Double,
//        primaryResults: Double,
//    ) {
//        if (offset <= primaryResults)
//            withContext(ioDispatcher) {
//                // Fetch data from the Network
//                val products =
//                    mercadoPagoNetwork.getProductsByQueryWithOffset(
//                        query = query, offset = offset
//                    )
//                // Store data in the database
//                database.productDao.insertListOfProducts(products = products.asDatabaseModel())
//
//            }
//
//    }

    companion object {
        @Volatile
        private var INSTANCE: ProductsRepository? = null

        fun getRepository(app: Application): ProductsRepository {
            return INSTANCE ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    app,
                    ProductsDatabase::class.java,
                    "product_history_database"
                ).build()
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
            remoteProducts.data.asDomainModel()
            database.saveProductsList(remoteProducts.data.asDatabaseModel())
        } else if (remoteProducts is ResultMercadoPago.Error) {
            throw remoteProducts.exception
        }
    }

    override suspend fun refreshProducts() {
        TODO("Not yet implemented")
    }

    override fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>> {
        return database.observeProducts()
    }

    override suspend fun refreshProduct(ProductId: String) {
        TODO("Not yet implemented")
    }

    override fun observeProduct(ProductId: String): LiveData<ResultMercadoPago<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun getProduct(
        ProductId: String,
        forceUpdate: Boolean,
    ): ResultMercadoPago<Product> {
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
        withContext(ioDispatcher) {
            coroutineScope {
                launch { database.deleteAllProducts() }
            }
        }
    }

    override suspend fun deleteProduct(ProductId: String) {
        database.deleteAllProducts()
    }
}