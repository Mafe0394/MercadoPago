package com.projects.mercadopago.data.domain

/**
 * Domain objects are plain Kotlin data classes that represent the things in our app. These are the
 * objects that should be displayed on screen, or manipulated by the app.
 */

/**
 * Product displayed on the screen
 */
data class Product(
    val productID:String,
    val title:String,
    val price:Long,
    val image:String,
    val imagesUrls:List<String>?=null,
    val descriptionID:String?=null
)

data class ProductDetail(
    val productID: String,
    val title: String,
    val picturesUrls:List<String>
)
