package com.projects.mercadopago.data.source

import androidx.lifecycle.LiveData
import com.projects.mercadopago.data.ProductsDataSource
import com.projects.mercadopago.data.database.DatabaseProduct
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.domain.asDomainModel
import com.projects.mercadopago.data.network.networkModels.ProductDetailsResponse
import com.projects.mercadopago.data.network.networkModels.ResponseModel
import com.projects.mercadopago.data.repository.ResultMercadoPago.Success
import com.projects.mercadopago.data.repository.ResultMercadoPago
import com.projects.mercadopago.data.repository.ResultMercadoPago.Error

class FakeDataSource(var products: MutableList<Product>? = mutableListOf()):ProductsDataSource {
    override fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getProducts(): ResultMercadoPago<List<Product>>? {
        products?.let { return Success(ArrayList(it)) }
        return Error(
            Exception("Products not found")
        )
    }

    override suspend fun refreshProducts(query: String): ResultMercadoPago<ResponseModel>? {
        TODO("Not yet implemented")
    }

    override fun observeVisitedProducts(): LiveData<ResultMercadoPago<List<Product>>> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshProduct(productID: String): ResultMercadoPago<ProductDetailsResponse>? {
        TODO("Not yet implemented")
    }

    override suspend fun saveProduct(product: DatabaseProduct) {
        products?.add(product.asDomainModel())
    }

    override suspend fun deleteAllProducts() {
        products?.clear()
    }

    override suspend fun saveProductsList(productsList: List<DatabaseProduct>) {
        TODO("Not yet implemented")
    }

    override suspend fun getProductDescription(productID: String): ResultMercadoPago<String>? {
        TODO("Not yet implemented")
    }

    override suspend fun getVisitedProducts(): ResultMercadoPago<List<Product>>? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteVisitedProducts() {
        TODO("Not yet implemented")
    }
}