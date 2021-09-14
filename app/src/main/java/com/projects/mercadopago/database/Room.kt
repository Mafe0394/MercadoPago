package com.projects.mercadopago.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/** A database tha stores Product information.
 * And a global method to get access to the database
 * */
@Database(entities = [DatabaseProduct::class], version = 1)
abstract class ProductsDatabase : RoomDatabase() {


    // Connects the database to the Dao
    abstract val productDao: ProductDao

    // Define a companion object, this allows to add functions on the ProductDataBase class.
    companion object {
        /** INSTANCE will keep a reference to any database returned via getInstance.
         * This will help us avoid repeatedly initializing the database, which is expensive.
         * The value of a volatile variable will never be cached, and all writes and
         * reads will be done to and from the main memory. It means that changes made by one
         * thread to shared data are visible to other threads.
         * */
        @Volatile
        private var INSTANCE: ProductsDatabase? = null

        /** Helper function to get the database.
         * If a database has already been retrieved, the previous database will be returned.
         * Otherwise, create a new database.
         * This function is threadsafe, and callers should cache the result for multiple database
         * calls to avoid overhead.
         * @param context The application context Singleton, used to get access to the filesystem
         * */

        fun getInstance(context: Context): ProductsDatabase {
            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchrinized block at a
            // time.
            synchronized(this) {

                // Copy the current value of Instance to a local variable so kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = INSTANCE

                // If INSTANCE is 'null' make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProductsDatabase::class.java,
                        "product_history_database"
                    )
                        // Wipes and rebuuilds instead of migrating if no  Migration object.
                        .fallbackToDestructiveMigration()
                        .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}


// Another way to initialize the database
private lateinit var INSTANCE1: ProductsDatabase

fun getDatabase(context: Context): ProductsDatabase {
    synchronized(ProductsDatabase::class.java) {
        if (!::INSTANCE1.isInitialized) {
            INSTANCE1 = Room.databaseBuilder(context.applicationContext,
                ProductsDatabase::class.java,
                "videos").build()
        }
    }
    return INSTANCE1
}