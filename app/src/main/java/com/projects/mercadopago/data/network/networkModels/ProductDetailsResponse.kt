package com.projects.mercadopago.data.network.networkModels

import com.squareup.moshi.Json

data class ProductDetailsResponse(
    val id: String?,
    @Json(name = "site_id")
    val siteId: String? = null,
    val title: String? = null,
    val subtitle: String? = null,
    @Json(name = "seller_id")
    val sellerId: Long? = null,
    @Json(name = "category_id")
    val categoryId: String? = null,
    @Json(name = "official_store_id")
    val officialStoreId: String? = null,
    val price: Long? = null,
    @Json(name = "base_price")
    val basePrice: Long? = null,
    @Json(name = "original_price")
    val originalPrice: Long? = null,
    @Json(name = "currency_id")
    val currencyId: String? = null,
    @Json(name = "initial_quantity")
    val initialQuantity: Long? = null,
    @Json(name = "available_quantity")
    val availableQuantity: Long? = null,
    @Json(name = "sold_quantity")
    val soldQuantity: Long? = null,
    @Json(name = "sale_terms")
    val saleTerms: List<SaleTerm?> = arrayListOf(),
    @Json(name = "buying_mode")
    val buyingMode: String? = null,
    @Json(name = "listing_type_id")
    val listingTypeId: String? = null,
    @Json(name = "start_time")
    val startTime: String? = null,
    @Json(name = "stop_time")
    val stopTime: String? = null,
    val condition: String? = null,
    val permalink: String? = null,
    @Json(name = "thumbnail_id")
    val thumbnailId: String? = null,
    val thumbnail: String? = null,
    @Json(name = "secure_thumbnail")
    val secureThumbnail: String? = null,
    val pictures: List<Picture?> = arrayListOf(),
    @Json(name = "video_id")
    val videoId: String? = null,
    val descriptions: List<Description> = arrayListOf(),
    @Json(name = "accepts_mercadopago")
    val acceptsMercadopago: Boolean = false,
    val shipping: Shipping? = null,
    @Json(name = "international_delivery_mode")
    val internationalDeliveryMode: String? = null,
    @Json(name = "seller_address")
    val sellerAddress: SellerAddress? = null,
    @Json(name = "seller_contact")
    val sellerContact: SellerContact? = null,
    val attributes: List<Attribute?> = arrayListOf(),
    @Json(name = "listing_source")
    val listingSource: String? = null,
    val status: String? = null,
    @Json(name = "sub_status")
    val subStatus: List<String?> = arrayListOf(),
    val tags: List<String?> = arrayListOf(),
    val warranty: String? = null,
    @Json(name = "catalog_product_id")
    val catalogProductId: String? = null,
    @Json(name = "domain_id")
    val domainId: String? = null,
    @Json(name = "parent_item_id")
    val parentItemId: String? = null,
    @Json(name = "differential_pricing")
    val differentialPricing: Long? = null,
    @Json(name = "automatic_relist")
    val automaticRelist: Boolean = false,
    @Json(name = "date_created")
    val dateCreated: String? = null,
    @Json(name = "last_updated")
    val lastUpdated: String? = null,
    @Json(name = "catalog_listing")
    val catalogListing: Boolean = false,
    val channels: List<String?> = arrayListOf(),
)

data class SaleTerm(
    val id: String?,
    val name: String?,
    @Json(name = "value_id")
    val valueId: String?,
    @Json(name = "value_name")
    val valueName: String?,
    @Json(name = "value_struct")
    val valueStruct: ValueStruct?,
    val values: List<Value?>,
)

data class SellerContact(
    val contact: String,
    @Json(name = "other_info")
    val otherInfo: String,
    @Json(name = "country_code")
    val countryCode: String,
    @Json(name = "area_code")
    val areaCode: String,
    val phone: String,
    @Json(name = "country_code2")
    val countryCode2: String,
    @Json(name = "area_code2")
    val areaCode2: String,
    val phone2: String,
    val email: String,
    val webpage: String,
)

data class ValueStruct(
    val number: Long?,
    val unit: String?,
)

data class Value(
    val id: String?,
    val name: String?,
    val struct: Struct?,
)

data class Struct(
    val number: Long?,
    val unit: String?,
)

data class Picture(
    val id: String?,
    val url: String?,
    @Json(name = "secure_url")
    val secureUrl: String?,
    val size: String?,
    @Json(name = "max_size")
    val maxSize: String?,
    val quality: String?,
)

data class Description(
    val id: String?,
)

data class Shipping(
    val mode: String?,
    @Json(name = "free_methods")
    val freeMethods: List<FreeMethod>?,
    val tags: List<String?>?,
    @Json(name = "local_pick_up")
    val localPickUp: Boolean?,
    @Json(name = "free_shipping")
    val freeShipping: Boolean?,
    @Json(name = "logistic_type")
    val logisticType: String?,
    @Json(name = "store_pick_up")
    val storePickUp: Boolean?,
)

data class FreeMethod(
    val id: Long?,
    val rule: Rule,
)

data class Rule(
    val default: Boolean,
    @Json(name = "free_mode")
    val freeMode: String?,
    @Json(name = "free_shipping_flag")
    val freeShippingFlag: Boolean,
)

data class SearchLocation(
    val neighborhood: Neighborhood?,
    val city: City2?,
    val state: State2?,
)

data class Neighborhood(
    val id: String?,
    val name: String?,
)

data class City2(
    val id: String?,
    val name: String?,
)

data class State2(
    val id: String?,
    val name: String?,
)

data class Variation(
    val id: Long?,
    val price: Long?,
    @Json(name = "attribute_combinations")
    val attributeCombinations: List<AttributeCombination>,
    @Json(name = "available_quantity")
    val availableQuantity: Long?,
    @Json(name = "sold_quantity")
    val soldQuantity: Long?,
    @Json(name = "picture_ids")
    val pictureIds: List<String?>,
)

data class AttributeCombination(
    val id: String?,
    val name: String?,
    @Json(name = "value_id")
    val valueId: String?,
    @Json(name = "value_name")
    val valueName: String?,
    val values: List<Value3>,
)

data class Value3(
    val id: String?,
    val name: String?,
)
