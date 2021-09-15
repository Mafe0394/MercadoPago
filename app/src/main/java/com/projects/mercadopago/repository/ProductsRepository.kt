package com.projects.mercadopago.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.projects.mercadopago.database.ProductsDatabase
import com.projects.mercadopago.domain.Product
import com.projects.mercadopago.domain.ResponseModel
import com.projects.mercadopago.domain.asDatabaseModel
import com.projects.mercadopago.domain.asDomainModel
import com.projects.mercadopago.network.MercadoPagoApiService
import com.projects.mercadopago.network.MercadoPagoNetwork
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/** [database] is a [ProductsDatabase] object that works as the class's constructor
 * parameter to access the Dao methods
 * */
class ProductsRepository(
    private val database: ProductsDatabase,
    private val mercadoPagoNetwork:MercadoPagoApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    private val _responseModel=MutableLiveData<ResponseModel>()
//    val responseModel:LiveData<ResponseModel> = _responseModel
    // Transformations.map uses a conversion function to convert one LiveData object into
    // another LiveData object. it only is calculated when an activity or fragment
    // is observing the returned LiveData property
    val products: LiveData<List<Product>> =
        Transformations.map(database.productDao.getVisitedProducts()) {
            it.asDomainModel()
        }

    val products1:LiveData<List<Product>> =
        Transformations.map(_responseModel){
            Timber.i("por aqui paso 1")
            it.asDomainModel()
        }

    fun getProductList():List<Product>?{
        return _responseModel.value?.asDomainModel()
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
            _responseModel.postValue(mercadoPagoNetwork.getProductsByQuery(query))
            Timber.i("results model is null ${_responseModel.value?.results==null} ${_responseModel.value?.results?.isEmpty()}")
        }
    }

    suspend fun getProductsQuery1(query: String){
        withContext(ioDispatcher){
            val products=mercadoPagoNetwork.getProductsByQuery(query = query)
            // Store data in the database
            database.productDao.insertListOfProducts(products = products.asDatabaseModel())
        }
    }

    suspend fun deleteSearch(){
        withContext(ioDispatcher){
            database.productDao.deleteProducts()
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
                    mercadoPagoNetwork.getProductsByQueryWithOffset(
                        query = query, offset = offset
                    )
                // Store data in the database
                database.productDao.insertListOfProducts(products = products.asDatabaseModel())

            }

    }

    companion object {
        @Volatile
        private var INSTANCE: ProductsRepository? = null

        fun getRepository(app: Application): ProductsRepository {
            return INSTANCE ?: synchronized(this) {
                val database = ProductsDatabase.getDatabase1(app)
                ProductsRepository(
                    database = database,
                    mercadoPagoNetwork = MercadoPagoNetwork.retrofitService
                ).also {
                    INSTANCE = it
                }
            }
        }
    }
}