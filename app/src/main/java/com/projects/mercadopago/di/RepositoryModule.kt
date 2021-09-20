package com.projects.mercadopago.di

import android.content.Context
import com.projects.mercadopago.data.repository.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    fun provideRepository(@ApplicationContext appContext:Context):ProductsRepository{
        return ProductsRepository.getRepository(appContext)
    }
}