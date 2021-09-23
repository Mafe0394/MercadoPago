package com.projects.mercadopago.di

import android.content.Context
import androidx.room.Room
import com.projects.mercadopago.data.database.ProductDao
import com.projects.mercadopago.data.database.ProductsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideProductDao(database: ProductsDatabase): ProductDao {
        return database.productDao
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): ProductsDatabase {
        return Room.databaseBuilder(
            appContext,
            ProductsDatabase::class.java,
            "product_history_database"
        ).fallbackToDestructiveMigration()
            .build()
    }
}


