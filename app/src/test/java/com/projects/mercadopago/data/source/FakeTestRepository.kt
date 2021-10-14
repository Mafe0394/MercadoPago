package com.projects.mercadopago.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.domain.ProductDetail
import com.projects.mercadopago.data.repository.IProductsRepository
import com.projects.mercadopago.data.repository.ResultMercadoPago
import kotlinx.coroutines.runBlocking

class FakeTestRepository : IProductsRepository {

    private val visitedProducts = mutableListOf<Product>()
    private val searchResults = mutableListOf<Product>()

    private val observableVisitedProducts =
        MutableLiveData<ResultMercadoPago<List<Product>>>(ResultMercadoPago.Success(visitedProducts))
    private val observableSearchResults =
        MutableLiveData<ResultMercadoPago<List<Product>>>(ResultMercadoPago.Success(searchResults))
    private val productDetail = MutableLiveData<ProductDetail>()

    private var shouldReturnError = false

    fun setShouldErrorError(value: Boolean) {
        shouldReturnError = value
    }

    private fun refreshLiveData() {
        observableVisitedProducts.postValue(ResultMercadoPago.Success(visitedProducts))
        observableSearchResults.postValue(ResultMercadoPago.Success(searchResults))
    }

    override suspend fun getProducts(query: String): ResultMercadoPago<List<Product>> {
        return if (shouldReturnError)
            ResultMercadoPago.Error(Exception("Error Test"))
        else {
            val resultList = listOf(
                Product(
                    "ProductID",
                    "title",
                    50000,
                    "ImageUrl"
                )
            )
            searchResults.addAll(resultList)
            refreshLiveData()
            ResultMercadoPago.Success(
                resultList
            )
        }
    }

    override fun observeVisitedProducts(): LiveData<ResultMercadoPago<List<Product>>> {
        return observableVisitedProducts
    }

    override fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>> {
        return observableSearchResults
    }

    override suspend fun refreshProducts() {
    }

    override suspend fun getVisitedProducts(): ResultMercadoPago<List<Product>> {
        return ResultMercadoPago.Success(visitedProducts)
    }

    override suspend fun getProduct(productID: String): ResultMercadoPago<Product> {
        val product = Product(
            productID,
            "title2",
            50,
            "url2"
        )
        visitedProducts.add(product)

        return ResultMercadoPago.Success(product)
    }

    override suspend fun deleteAllProducts() {
        searchResults.removeAll { true }
    }

    override suspend fun getProductDescription(productID: String): ResultMercadoPago<String> {
        return if (shouldReturnError)
            ResultMercadoPago.Error(Exception("Error in Test"))
        else
            ResultMercadoPago.Success("$productID description for test")
    }

    override suspend fun deleteVisitedProducts() {
        visitedProducts.removeAll { true }
    }

    override suspend fun getProductsPaging(value: String?): LiveData<PagingData<Product>> {
        return MutableLiveData()
    }
}