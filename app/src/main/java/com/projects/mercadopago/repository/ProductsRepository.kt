package com.projects.mercadopago.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.projects.mercadopago.database.ProductsDatabase
import com.projects.mercadopago.domain.Product
import com.projects.mercadopago.domain.asDatabaseModel
import com.projects.mercadopago.domain.asDomainModel
import com.projects.mercadopago.network.MercadoApiStatus
import com.projects.mercadopago.network.MercadoPagoNetwork
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/** [database] is a [ProductsDatabase] object that works as the class's constructor
 * parameter to access the Dao methods
 * */
class ProductsRepository(
    private val database: ProductsDatabase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    // Transformations.map uses a conversion function to convert one LiveData object into
    // another LiveData object. it only is calculated when an activity or fragment
    // is observing the returned LiveData property
    val products: LiveData<List<Product>> =
        Transformations.map(database.productDao.getVisitedProducts()) {
            it.asDomainModel()
        }

    /**
     * Refresh the products stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     */
    // Get products from a Query
    suspend fun getProductsQuery(query: String) {
        withContext(ioDispatcher) {
            // Fetch data from the Network
            val response =
                MercadoPagoNetwork.retrofitService.getProductsByQuery(query = query)
            // Store data in the database
            database.productDao.insertListOfProducts(products = response.asDatabaseModel())
        }

    }

    suspend fun getMoreProducts(
        query: String,
        offset: Double,
        limit: Double,
        primaryResults: Double,
    ) {
        if (offset <= primaryResults)
            withContext(ioDispatcher) {
                // Fetch data from the Network
                val products =
                    MercadoPagoNetwork.retrofitService.getProductsByQueryWithOffset(
                        query = query, offset = offset
                    )
                // Store data in the database
                database.productDao.insertListOfProducts(products = products.asDatabaseModel())

            }

    }
}