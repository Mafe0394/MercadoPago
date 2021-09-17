package com.projects.mercadopago.data.network

import com.projects.mercadopago.data.network.networkModels.ResponseModel
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
}


