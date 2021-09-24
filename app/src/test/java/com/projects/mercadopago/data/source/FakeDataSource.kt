package com.projects.mercadopago.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.projects.mercadopago.data.ProductsDataSource
import com.projects.mercadopago.data.database.DatabaseProduct
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.domain.asDomainModel
import com.projects.mercadopago.data.domain.asDomainModellist
import com.projects.mercadopago.data.network.networkModels.ProductDetailsResponse
import com.projects.mercadopago.data.network.networkModels.ResponseModel
import com.projects.mercadopago.data.network.networkModels.ResultModel
import com.projects.mercadopago.data.repository.ResultMercadoPago.Success
import com.projects.mercadopago.data.repository.ResultMercadoPago
import com.projects.mercadopago.data.repository.ResultMercadoPago.Error

class FakeDataSource(
    private var productsRemote: MutableList<ResultModel>? = mutableListOf(),
    private var productsDatabase: MutableList<DatabaseProduct>?= mutableListOf(),
) : ProductsDataSource {

    override fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>> {
        productsDatabase?.let { return MutableLiveData(Success(it.asDomainModel())) }
        return MutableLiveData(
            Error(
                Exception("No productos in fake database")
            ))
    }

    override suspend fun getProducts(): ResultMercadoPago<List<Product>>? {
        productsDatabase?.let {
            return Success(it.asDomainModel())
        }
        return Error(
            Exception("Products not found")
        )
    }

    override suspend fun refreshProducts(query: String): ResultMercadoPago<ResponseModel>? {
        return productsRemote?.let {
            Success(ResponseModel(results = it))
        }
    }

    override fun observeVisitedProducts(): LiveData<ResultMercadoPago<List<Product>>> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshProduct(productID: String): ResultMercadoPago<ProductDetailsResponse>? {
        TODO("Not yet implemented")
    }

    override suspend fun saveProduct(product: DatabaseProduct) {
        productsDatabase?.add(product)
    }

    override suspend fun deleteAllProducts() {
        productsDatabase?.clear()
    }

    override suspend fun saveProductsList(productsList: List<DatabaseProduct>) {
        productsDatabase?.clear()
        productsDatabase?.addAll(productsList)
    }

    override suspend fun getProductDescription(productID: String): ResultMercadoPago<String>? {
        TODO("Not yet implemented")
    }

    override suspend fun getVisitedProducts(): ResultMercadoPago<List<Product>>? {
        return productsDatabase?.let {
            Success(it.asDomainModel())
        }
    }

    override suspend fun deleteVisitedProducts() {
        TODO("Not yet implemented")
    }
}