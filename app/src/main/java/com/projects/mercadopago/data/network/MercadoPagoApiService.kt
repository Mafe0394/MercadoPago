package com.projects.mercadopago.data.network

import com.projects.mercadopago.data.network.networkModels.ProductDetails
import com.projects.mercadopago.data.network.networkModels.ProductDetailsResponse
import com.projects.mercadopago.data.network.networkModels.ResponseModel
import com.projects.mercadopago.data.repository.ResultMercadoPago
import retrofit2.http.GET
import retrofit2.http.Query

interface MercadoPagoApiService {
    @GET("sites/MCO/search")
    suspend fun getProductsByQuery(@Query("q") query: String): ResponseModel

    @GET("sites/MCO/search")
    suspend fun getProductsByQueryWithOffset(
        @Query("q")
        query: String,
        @Query("offset")
        offset: Int,
    ): ResponseModel

    @GET("items?ids=MCO452454256")
    suspend fun getProductDetails(
        @Query("ids")
        productID: String
    ): List<ProductDetailsResponse>
}


