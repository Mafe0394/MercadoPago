package com.projects.mercadopago.data.database

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
    fun getProductsFromDatabase(): List<DatabaseProduct>

    @Query("select * from databaseproduct")
    fun observeProducts(): LiveData<List<DatabaseProduct>>

    /* Insert a list of products fetched from the network into the database.
    * Overwrite the database entry if the product is already present in the database,
    * to do this we use onConflict argument to OnConflictStrategy.REPLACE*/
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = DatabaseProduct::class)
    fun insertListOfProducts(products:List<DatabaseProduct>)


    @Query("DELETE FROM databaseproduct")
    suspend fun deleteProducts()

    /* Visited Products*/

    @Query("select * from databaseproductdetails")
    fun getVisitedProducts(): List<DatabaseProduct>

    @Query("select * from databaseproductdetails")
    fun observeVisitedProducts(): LiveData<List<DatabaseProduct>>

    /* Insert a product fetched from the network into the database.*/
    @Insert(onConflict = OnConflictStrategy.REPLACE,entity = DatabaseProductDetails::class)
    fun insertProduct(product: DatabaseProduct)

    @Query("DELETE FROM databaseproductdetails")
    suspend fun deleteVisitedProducts()

}