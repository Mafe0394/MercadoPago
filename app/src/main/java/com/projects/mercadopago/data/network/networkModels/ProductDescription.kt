package com.projects.mercadopago.data.network.networkModels

import com.squareup.moshi.Json



data class ProductDescription(
    val text: String,
    @Json(name="plain_text")
    val plainText: String,
    @Json(name="last_updated")
    val lastUpdated: String,
    @Json(name="date_created")
    val dateCreated: String,
    val snapshot: Snapshot
)

data class Snapshot(
    val url: String,
    val width: Long,
    val height: Long,
    val status: String
)