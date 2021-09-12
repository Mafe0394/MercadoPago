package com.projects.mercadopago.domain

data class ProductModel (
    val id: String,
    val siteID: String,
    val title: String,
    val seller: Seller,
    val price: Long,
    val prices: Prices,
    val salePrice: Any? = null,
    val currencyID: String,
    val availableQuantity: Long,
    val soldQuantity: Long,
    val buyingMode: String,
    val listingTypeID: String,
    val stopTime: String,
    val condition: String,
    val permalink: String,
    val thumbnail: String,
    val thumbnailID: String,
    val acceptsMercadopago: Boolean,
    val installments: Installments,
    val address: Address,
    val shipping: Shipping,
    val sellerAddress: SellerAddress,
    val attributes: List<Attribute>,
    val differentialPricing: DifferentialPricing,
    val originalPrice: Long,
    val categoryID: String,
    val officialStoreID: Any? = null,
    val domainID: String,
    val catalogProductID: Any? = null,
    val tags: List<String>,
    val orderBackend: Long,
    val useThumbnailID: Boolean,
    val offerScore: Any? = null,
    val offerShare: Any? = null
)

data class Address (
    val stateID: String,
    val stateName: String,
    val cityID: String,
    val cityName: String
)

data class Attribute (
    val valueStruct: Any? = null,
    val source: Long,
    val id: String,
    val valueID: String? = null,
    val values: List<Value>,
    val attributeGroupID: String,
    val attributeGroupName: String,
    val name: String,
    val valueName: String
)

data class Value (
    val id: String? = null,
    val name: String,
    val struct: Any? = null,
    val source: Long
)

data class DifferentialPricing (
    val id: Long
)

data class Installments (
    val quantity: Long,
    val amount: Double,
    val rate: Long,
    val currencyID: String
)

data class Prices (
    val id: String,
    val prices: List<Price>,
    val presentation: Presentation,
    val paymentMethodPrices: List<Any?>,
    val referencePrices: List<Any?>,
    val purchaseDiscounts: List<Any?>
)

data class Presentation (
    val displayCurrency: String
)

data class Price (
    val id: String,
    val type: String,
    val amount: Long,
    val regularAmount: Long? = null,
    val currencyID: String,
    val lastUpdated: String,
    val conditions: Conditions,
    val exchangeRateContext: String,
    val metadata: Metadata
)

data class Conditions (
    val contextRestrictions: List<String>,
    val startTime: String? = null,
    val endTime: String? = null,
    val eligible: Boolean
)

data class Metadata (
    val campaignID: String? = null,
    val promotionID: String? = null,
    val promotionType: String? = null
)

data class Seller (
    val id: Long,
    val permalink: Any? = null,
    val registrationDate: Any? = null,
    val carDealer: Boolean,
    val realEstateAgency: Boolean,
    val tags: Any? = null
)

data class SellerAddress (
    val id: String,
    val comment: String,
    val addressLine: String,
    val zipCode: String,
    val country: City,
    val state: City,
    val city: City,
    val latitude: String,
    val longitude: String
)

data class City (
    val id: String,
    val name: String
)

data class Shipping (
    val freeShipping: Boolean,
    val mode: String,
    val tags: List<String>,
    val logisticType: String,
    val storePickUp: Boolean
)
