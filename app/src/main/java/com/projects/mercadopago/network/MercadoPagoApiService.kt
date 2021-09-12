package com.projects.mercadopago.network

import com.projects.mercadopago.domain.ProductModel
import com.projects.mercadopago.domain.ResponseModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.mercadolibre.com/"
private const val SITE_ID="MCO"

enum class MercadoPagoCategoryFilter(val value:String){
    // ToDo(Replace with my own filters)
    SHOW_RENT("rent"),
    SHOW_BUY("buy"),
    SHOW_ALL("all")
}

// Moshi object to use as a converter factory
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


interface MercadoPagoApiService {
    @GET("sites/MCO/search?q=celular iphone X64gb")
    suspend fun getProducts(): ResponseModel
}


object MercadoPagoNetwork {
    /* Retrofit Object
    * Retrofit needs the base URI for the web service and the converter factory.*/
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()
    val retrofitService: MercadoPagoApiService by lazy {
        retrofit.create(MercadoPagoApiService::class.java)
    }
}