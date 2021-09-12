package com.projects.mercadopago.domain

import com.squareup.moshi.Json



data class ProductModel (
    val id: String,
    @Json(name = "site_id")
    val siteID: String,
    val title: String,
    val price: Long
)