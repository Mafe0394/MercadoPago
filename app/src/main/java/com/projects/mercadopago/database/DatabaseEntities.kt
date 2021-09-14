package com.projects.mercadopago.database

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.projects.mercadopago.domain.Product
import com.squareup.moshi.Json

data class DatabaseProduct(
    @PrimaryKey
    val productID:String,
    val title:String,
    val price:Long,
    @ColumnInfo(name = "base_price")
    val basePrice:Long,
    @ColumnInfo(name = "stop_time")
    val stopTime:String,
    val condition:String,
    val permalink:String,
    val thumbnail:String,
    @ColumnInfo(name = "last_visit_time")
    val lastVisitTime:Long=System.currentTimeMillis()
)

