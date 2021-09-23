package com.projects.mercadopago.data.network

import androidx.lifecycle.LiveData
import com.projects.mercadopago.data.ProductsDataSource
import com.projects.mercadopago.data.database.DatabaseProduct
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.network.networkModels.ProductDetailsResponse
import com.projects.mercadopago.data.network.networkModels.ResponseModel
import com.projects.mercadopago.data.repository.ResultMercadoPago
import com.projects.mercadopago.data.repository.ResultMercadoPago.Success
import retrofit2.HttpException
import javax.inject.Inject

const val BASE_URL = "https://api.mercadolibre.com/"
const val SITE_ID = "MCO"

class MercadoPagoNetwork  @Inject constructor(
    private val retrofitService:MercadoPagoApiService
) : ProductsDataSource {



//    @Inject lateinit var moshi: Moshi
    // Moshi object to use as a converter factory
//    private val moshi = Moshi.Builder()
//        .add(KotlinJsonAdapterFactory())
//        .build()

    /* Retrofit Object
    * Retrofit needs the base URI for the web service and the converter factory.*/
//    private val retrofit = Retrofit.Builder()
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .baseUrl(BASE_URL)
//        .build()
//    private val retrofitService: MercadoPagoApiService by lazy {
//        retrofit.create(MercadoPagoApiService::class.java)
//    }

    override fun observeProducts(): LiveData<ResultMercadoPago<List<Product>>> {
        TODO("Not implemented")
    }

    override suspend fun getProducts(): ResultMercadoPago<List<Product>>? {
        TODO("Not implemented")
    }

    override suspend fun refreshProducts(query: String): ResultMercadoPago<ResponseModel>? =
        try {
            Success(retrofitService.getProductsByQuery(query))
        } catch (e: HttpException) {
            ResultMercadoPago.Error(e)
        }

    override fun observeVisitedProducts(): LiveData<ResultMercadoPago<List<Product>>> {
        TODO("Not implemented")
    }

    override suspend fun refreshProduct(productID: String): ResultMercadoPago<ProductDetailsResponse>? =
        try {
            Success(retrofitService.getProductDetails(productID = productID))

        } catch (e: HttpException) {
            ResultMercadoPago.Error(e
            )
        }

    override suspend fun saveProduct(product: DatabaseProduct) {
        TODO("Not implemented")
    }

    override suspend fun deleteAllProducts() {
        TODO("Not implemented")
    }

    override suspend fun saveProductsList(productsList: List<DatabaseProduct>) {
        TODO("Not implemented")
    }

    override suspend fun getProductDescription(productID: String): ResultMercadoPago<String>? =
        try{
            Success(retrofitService.getProductDescription(productID).plainText)
        }catch (e:HttpException){
            ResultMercadoPago.Error(e)
        }

    override suspend fun getVisitedProducts(): ResultMercadoPago<List<Product>>? {
        TODO("Not implemented")
    }

    override suspend fun deleteVisitedProducts() {
        TODO("Not implemented")
    }


}