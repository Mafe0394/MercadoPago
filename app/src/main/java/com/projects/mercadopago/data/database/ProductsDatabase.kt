package com.projects.mercadopago.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

/** A database tha stores Product information.
 * And a global method to get access to the database
 * Note that exportSchema should be true in production databases.
 */
@Database(entities = [DatabaseProduct::class,
    DatabaseProductDetails::class], version = 3)
abstract class ProductsDatabase : RoomDatabase() {


    // Connects the database to the Dao
    abstract val productDao: ProductDao

}


