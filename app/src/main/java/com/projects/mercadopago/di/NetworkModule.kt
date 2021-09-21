package com.projects.mercadopago.di

import com.projects.mercadopago.data.network.MercadoPagoApiService
import com.projects.mercadopago.data.network.MercadoPagoNetwork
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideMoshiConverter(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://api.mercadolibre.com/")
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit.Builder): MercadoPagoApiService {
        return retrofit.build()
            .create(MercadoPagoApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesMercadoPagoNetwork(mercadoPagoApiService: MercadoPagoApiService): MercadoPagoNetwork {
        return MercadoPagoNetwork(mercadoPagoApiService)
    }
}

