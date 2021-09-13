package com.projects.mercadopago.domain

import com.squareup.moshi.Json

data class ProductModel (
    val id: String? = null,

    @Json(name="site_id")
    val siteID: String? = null,

    val title: String? = null,
    val seller: Seller? = null,
    val price: Long? = null,
    val prices: Prices? = null,

    @Json(name="sale_price")
    val salePrice: String? = null,

    @Json(name="currency_id")
    val currencyID: String? = null,

    @Json(name="available_quantity")
    val availableQuantity: Long? = null,

    @Json(name="sold_quantity")
    val soldQuantity: Long? = null,

    @Json(name="buying_mode")
    val buyingMode: String? = null,

    @Json(name="listing_type_id")
    val listingTypeID: String? = null,

    @Json(name="stop_time")
    val stopTime: String? = null,

    val condition: String? = null,
    val permalink: String? = null,
    var thumbnail: String? = null,

    @Json(name="thumbnail_id")
    val thumbnailID: String? = null,

    @Json(name="accepts_mercadopago")
    val acceptsMercadopago: Boolean? = null,

    val installments: Installments? = null,
    val address: Address? = null,
    val shipping: Shipping? = null,

    @Json(name="seller_address")
    val sellerAddress: SellerAddress? = null,

    val attributes: List<Attribute>? = null,

    @Json(name="differential_pricing")
    val differentialPricing: DifferentialPricing? = null,

    @Json(name="original_price")
    val originalPrice: Long? = null,

    @Json(name="category_id")
    val categoryID: String? = null,

    @Json(name="official_store_id")
    val officialStoreID: Long? = null,

    @Json(name="domain_id")
    val domainID: String? = null,

    @Json(name="catalog_product_id")
    val catalogProductID: String? = null,

    val tags: List<String>? = null,

    @Json(name="order_backend")
    val orderBackend: Long? = null,

    @Json(name="use_thumbnail_id")
    val useThumbnailID: Boolean? = null,

    @Json(name="offer_score")
    val offerScore: String? = null,

    @Json(name="offer_share")
    val offerShare: String? = null,

    @Json(name="catalog_listing")
    val catalogListing: Boolean? = null
)



