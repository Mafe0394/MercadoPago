package com.projects.mercadopago.di

import com.projects.mercadopago.data.database.ProductLocalDataSource
import com.projects.mercadopago.data.network.MercadoPagoNetwork
import com.projects.mercadopago.data.repository.IProductsRepository
import com.projects.mercadopago.data.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        mercadoPagoLocal: ProductLocalDataSource,
        mercadoPagoNetwork: MercadoPagoNetwork,
    ): IProductsRepository {
        return ProductsRepository(mercadoPagoLocal, mercadoPagoNetwork)
    }

    @Singleton
    @Provides
    fun provideIRepository(repository: ProductsRepository): IProductsRepository {
        return repository
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule2 {
    @Binds
    abstract fun bindMercadoPagoRepository(mercadoPagoNetwork: ProductsRepository): IProductsRepository
}



