package com.projects.mercadopago.data.repository

import com.projects.mercadopago.data.database.DatabaseProduct
import com.projects.mercadopago.data.database.ProductsDatabase
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.domain.asDomainModel
import com.projects.mercadopago.data.domain.asDomainModellist
import com.projects.mercadopago.data.network.networkModels.ResultModel
import com.projects.mercadopago.data.source.FakeDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductsRepositoryTest {

    private val product1 = ResultModel(
        id = "id1",
        title = "title",
        price = 50,
        stopTime = "stoptime",
        condition = "condition",
        permalink = "permalink",
        thumbnail = "thumbnail",
        originalPrice = 0
    )
    private val product2 = ResultModel(
        id = "id2",
        title = "title",
        price = 50,
        stopTime = "stoptime",
        condition = "condition",
        permalink = "permalink",
        thumbnail = "thumbnail",
        originalPrice = 0
    )
    private val product3 = DatabaseProduct(
        productID = "id3",
        title = "title",
        price = 50,
        basePrice = 0,
        stopTime = "stopTime",
        condition = "condition",
        permalink = "permalink",
        thumbnail = "Thumbnail",
    )
    private val remoteProducts = listOf(product1, product2).sortedBy { it.id }
    private val localProducts = listOf(product3).sortedBy { it.productID }
    private val newProducts = listOf(product3).sortedBy { it.productID }

    private lateinit var productsRemoteDataSource: FakeDataSource
    private lateinit var productsLocalDataSource: FakeDataSource

    private lateinit var productsRepository: ProductsRepository

    @Before
    fun createRepository() {
        productsRemoteDataSource = FakeDataSource(productsRemote = remoteProducts.toMutableList())
        productsLocalDataSource = FakeDataSource(productsDatabase = localProducts.toMutableList())
        // Get a reference to the class under test
        productsRepository = ProductsRepository(
            mercadoPagoLocal = productsLocalDataSource,
            mercadoPagoNetwork = productsRemoteDataSource,
            ioDispatcher = Dispatchers.Unconfined)
    }

    @Test
    fun getProducts_requestAllProductsFromRemoteDataSource() = runBlockingTest {
        val products = productsRepository.getProducts("query") as ResultMercadoPago.Success

        assertThat(products.data, IsEqual(remoteProducts.asDomainModellist()))
    }

    @Test
    fun getVisitedProducts_requestAllProductsFromDatabase()= runBlockingTest {
        val products=productsRepository.getVisitedProducts() as ResultMercadoPago.Success

        assertThat(products.data,IsEqual(localProducts.asDomainModel()))
    }
}