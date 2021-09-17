package com.projects.mercadopago.data.network.networkModels

import com.squareup.moshi.Json


data class ProductDetailsResponse(
    val code: Long,
    val body: Body
)

data class Body(
    val id: String,
    @Json(name="site_id")
    val siteId: String,
    val title: String,
    val subtitle: Any,
    @Json(name="seller_id")
    val sellerId: Long,
    @Json(name="category_id")
    val categoryId: String,
    @Json(name="official_store_id")
    val officialStoreId: Any,
    val price: Long,
    @Json(name="base_price")
    val basePrice: Long,
    @Json(name="original_price")
    val originalPrice: Any,
    @Json(name="currency_id")
    val currencyId: String,
    @Json(name="initial_quantity")
    val initialQuantity: Long,
    @Json(name="available_quantity")
    val availableQuantity: Long,
    @Json(name="sold_quantity")
    val soldQuantity: Long,
    @Json(name="sale_terms")
    val saleTerms: List<SaleTerm>,
    @Json(name="buying_mode")
    val buyingMode: String,
    @Json(name="listing_type_id")
    val listingTypeId: String,
    @Json(name="start_time")
    val startTime: String,
    @Json(name="stop_time")
    val stopTime: String,
    val condition: String,
    val permalink: String,
    @Json(name="thumbnail_id")
    val thumbnailId: String,
    val thumbnail: String,
    @Json(name="secure_thumbnail")
    val secureThumbnail: String,
    val pictures: List<Picture>,
    @Json(name="video_id")
    val videoId: String,
    val descriptions: List<Description>,
    @Json(name="accepts_mercadopago")
    val acceptsMercadopago: Boolean,
    @Json(name="non_mercado_pago_payment_methods")
    val nonMercadoPagoPaymentMethods: List<Any>,
    val shipping: Shipping,
    @Json(name="international_delivery_mode")
    val internationalDeliveryMode: String,
    @Json(name="seller_address")
    val sellerAddress: SellerAddress,
    @Json(name="seller_contact")
    val sellerContact: Any,
    val location: Location,
    @Json(name="coverage_areas")
    val coverageAreas: List<Any>,
    val attributes: List<Attribute>,
    val warnings: List<Any>,
    @Json(name="listing_source")
    val listingSource: String,
    val variations: List<Variation>,
    val status: String,
    @Json(name="sub_status")
    val subStatus: List<Any>,
    val tags: List<String>,
    val warranty: String,
    @Json(name="catalog_product_id")
    val catalogProductId: String,
    @Json(name="domain_id")
    val domainId: String,
    @Json(name="parent_item_id")
    val parentItemId: Any,
    @Json(name="differential_pricing")
    val differentialPricing: Any,
    @Json(name="deal_ids")
    val dealIds: List<Any>,
    @Json(name="automatic_relist")
    val automaticRelist: Boolean,
    @Json(name="date_created")
    val dateCreated: String,
    @Json(name="last_updated")
    val lastUpdated: String,
    val health: Double,
    @Json(name="catalog_listing")
    val catalogListing: Boolean,
    val channels: List<String>
)

data class SaleTerm(
    val id: String,
    val name: String,
    @Json(name="value_id")
    val valueId: String?,
    @Json(name="value_name")
    val valueName: String,
    @Json(name="value_struct")
    val valueStruct: ValueStruct?,
    val values: List<Value>
)

data class ValueStruct(
    val number: Long,
    val unit: String
)

data class Value(
    val id: String?,
    val name: String,
    val struct: Struct?
)

data class Struct(
    val number: Long,
    val unit: String
)

data class Picture(
    val id: String,
    val url: String,
    @Json(name="secure_url")
    val secureUrl: String,
    val size: String,
    @Json(name="max_size")
    val maxSize: String,
    val quality: String
)

data class Description(
    val id: String
)

data class Shipping(
    val mode: String,
    @Json(name="free_methods")
    val freeMethods: List<FreeMethod>,
    val tags: List<String>,
    val dimensions: Any,
    @Json(name="local_pick_up")
    val localPickUp: Boolean,
    @Json(name="free_shipping")
    val freeShipping: Boolean,
    @Json(name="logistic_type")
    val logisticType: String,
    @Json(name="store_pick_up")
    val storePickUp: Boolean
)

data class FreeMethod(
    val id: Long,
    val rule: Rule
)

data class Rule(
    val default: Boolean,
    @Json(name="free_mode")
    val freeMode: String,
    @Json(name="free_shipping_flag")
    val freeShippingFlag: Boolean,
    val value: Any
)

data class City(
    val id: String,
    val name: String
)

data class State(
    val id: String,
    val name: String
)

data class Country(
    val id: String,
    val name: String
)

data class SearchLocation(
    val neighborhood: Neighborhood,
    val city: City2,
    val state: State2
)

data class Neighborhood(
    val id: String,
    val name: String
)

data class City2(
    val id: String,
    val name: String
)

data class State2(
    val id: String,
    val name: String
)

data class Location(
    val dummy:Any
)

data class Variation(
    val id: Long,
    val price: Long,
    @Json(name="attribute_combinations")
    val attributeCombinations: List<AttributeCombination>,
    @Json(name="available_quantity")
    val availableQuantity: Long,
    @Json(name="sold_quantity")
    val soldQuantity: Long,
    @Json(name="sale_terms")
    val saleTerms: List<Any>,
    @Json(name="picture_ids")
    val pictureIds: List<String>,
    @Json(name="catalog_product_id")
    val catalogProductId: Any
)

data class AttributeCombination(
    val id: String,
    val name: String,
    @Json(name="value_id")
    val valueId: String,
    @Json(name="value_name")
    val valueName: String,
    @Json(name="value_struct")
    val valueStruct: Any,
    val values: List<Value3>
)

data class Value3(
    val id: String,
    val name: String,
    val struct: Any
)
