package com.projects.mercadopago.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseProduct constructor(
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

@Entity
data class DatabaseProduct1 constructor(
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

