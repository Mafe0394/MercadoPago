package com.projects.mercadopago.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
    /* Fetch all the products from the database.
    * This method returns LiveData, so that the data displayed in the UI is refreshed
    * whenever the data in the database is changed*/
    @Query("select * from databaseproduct")
    fun getVisitedProducts(): LiveData<List<DatabaseProduct>>

    /* Insert a list of products fetched from the network into the database.
    * Overwrite the database entry if the product is already present in the database,
    * to do this we use onConflict argument to OnConflictStrategy.REPLACE*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListOfProducts(products:List<DatabaseProduct>)

    /* Insert a product fetched from the network into the database.*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: DatabaseProduct)

    @Query("DELETE FROM databaseproduct")
    suspend fun deleteProducts()

}