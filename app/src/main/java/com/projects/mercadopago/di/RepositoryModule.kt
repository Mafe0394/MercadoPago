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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        mercadoPagoLocal: ProductLocalDataSource,
        mercadoPagoNetwork: MercadoPagoNetwork,
        ioDispatcher: CoroutineDispatcher
    ): IProductsRepository {
        return ProductsRepository(mercadoPagoLocal, mercadoPagoNetwork,ioDispatcher)
    }

    @Singleton
    @Provides
    fun provideIRepository(repository: ProductsRepository): IProductsRepository {
        return repository
    }

    @Singleton
    @Provides
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule2 {
    @Binds
    abstract fun bindMercadoPagoRepository(mercadoPagoNetwork: ProductsRepository): IProductsRepository
}



