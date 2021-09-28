package com.projects.mercadopago.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.repository.IProductsRepository
import com.projects.mercadopago.data.repository.ResultMercadoPago
import kotlinx.coroutines.runBlocking

class FakeTestRepository:IProductsRepository {

    // Variable representing the current list of tasks
    var productsServidata: LinkedHashMap<String, Product> = LinkedHashMap()

    private val observableProducts = MutableLiveData<ResultMercadoPago<List<Product>>>()

    // To test is better to have some tasks already in our repository,
    // Instead of calling saveTask, we can add a helper method specifically for tests
    // that lets us add tasks
    fun addTasks(vararg  products:Product){
        for(product in products){
            productsServidata[product.productID]=product
        }
        runBlocking { refreshProducts() }
    }

    override suspend fun getProducts(query: String): ResultMercadoPago<List<Product>>? {
        return ResultMercadoPago.Success(productsServidata.values.toList())
    }

    override fun observeVisitedProducts(): LiveData<ResultMercadoPago<List<Product>>> {
        TODO("Not yet implemented")
    }

    override fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>> {
        runBlocking {
            refreshProducts()
        }
        return observableProducts
    }

    override suspend fun refreshProducts() {
        observableProducts.value = getProducts("query")
    }

    override suspend fun getVisitedProducts(): ResultMercadoPago<List<Product>>? {
        TODO("Not yet implemented")
    }

    override suspend fun getProduct(productID: String): ResultMercadoPago<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllProducts() {
        TODO("Not yet implemented")
    }

    override suspend fun getProductDescription(productID: String): ResultMercadoPago<String> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteVisitedProducts() {
        TODO("Not yet implemented")
    }

    override suspend fun getProductsPaging(value: String?): LiveData<PagingData<Product>> {
        TODO("Not yet implemented")
    }
}