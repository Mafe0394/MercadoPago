package com.projects.mercadopago.di

import com.projects.mercadopago.data.ProductsDataSource
import com.projects.mercadopago.data.database.ProductLocalDataSource
import com.projects.mercadopago.data.network.MercadoPagoNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class MercadoPagoLocal

@Qualifier
annotation class MercadoPagoService

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModuleLocal {
    @MercadoPagoLocal
    @Singleton
    @Binds
    abstract fun bindLocalDataProduct(impl: ProductLocalDataSource): ProductsDataSource

    @MercadoPagoService
    @Singleton
    @Binds
    abstract fun bindRemoteDataProduct(impl: MercadoPagoNetwork): ProductsDataSource
}