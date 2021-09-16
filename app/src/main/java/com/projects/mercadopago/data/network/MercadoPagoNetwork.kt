package com.projects.mercadopago.data.network

import androidx.lifecycle.LiveData
import com.projects.mercadopago.data.ProductsDataSource
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.repository.ResultMercadoPago
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object MercadoPagoNetwork:ProductsDataSource {

    private const val BASE_URL = "https://api.mercadolibre.com/"
    private const val SITE_ID = "MCO"
    // Moshi object to use as a converter factory
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    /* Retrofit Object
    * Retrofit needs the base URI for the web service and the converter factory.*/
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()
    val retrofitService: MercadoPagoApiService by lazy {
        retrofit.create(MercadoPagoApiService::class.java)
    }

    override fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getProducts(): ResultMercadoPago<List<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshProducts() {
        TODO("Not yet implemented")
    }

    override fun observeProduct(ProductId: String): LiveData<ResultMercadoPago<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun getProduct(ProductId: String): ResultMercadoPago<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshProduct(ProductId: String) {
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
        TODO("Not yet implemented")
    }

    override suspend fun deleteProduct(ProductId: String) {
        TODO("Not yet implemented")
    }
}