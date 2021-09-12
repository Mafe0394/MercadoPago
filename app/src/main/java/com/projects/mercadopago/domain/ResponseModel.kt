package com.projects.mercadopago.domain

import com.squareup.moshi.Json

data class ResponseModel (
    @Json(name = "site_id")
    val siteID: String="",
    val countryDefaultTimeZone: String="",
    val query: String="",
    val paging: Paging? = null,
    val results: List<ProductModel>? = null,
    val sort: Sort? = null,
    val availableSorts: List<Sort>? = null,
    val filters: List<Any?>? = null,
    val availableFilters: List<AvailableFilter>? = null
)

data class AvailableFilter (
    val id: String="",
    val name: String="",
    val type: String="",
    val values: List<AvailableFilterValue>? = null
)

data class AvailableFilterValue (
    val id: String="",
    val name: String="",
    val results: Long=0L
)

data class Sort (
    val id: String="",
    val name: String=""
)

data class Paging (
    val total: Long=0L,
    val primaryResults: Long=0L,
    val offset: Long=0L,
    val limit: Long=0L
)

data class Result (
    val id: String="",
    val siteID: String="",
    val title: String="",
    val seller: Seller? = null,
    val price: Long=0L,
    val prices: Prices? = null,
    val salePrice: Any? = null,
    val currencyID: CurrencyID? = null,
    val availableQuantity: Long=0L,
    val soldQuantity: Long=0L,
    val buyingMode: BuyingMode? = null,
    val listingTypeID: ListingTypeID? = null,
    val stopTime: String="",
    val condition: Condition? = null,
    val permalink: String="",
    val thumbnail: String="",
    val thumbnailID: String="",
    val acceptsMercadopago: Boolean? = null,
    val installments: Installments? = null,
    val address: Address? = null,
    val shipping: Shipping? = null,
    val sellerAddress: SellerAddress? = null,
    val attributes: List<Attribute>? = null,
    val differentialPricing: DifferentialPricing? = null,
    val originalPrice: Long=0L,
    val categoryID: String="",
    val officialStoreID: Long=0L,
    val domainID: String="",
    val catalogProductID: String="",
    val tags: List<ResultTag>? = null,
    val orderBackend: Long=0L,
    val useThumbnailID: Boolean? = null,
    val offerScore: Any? = null,
    val offerShare: Any? = null,
    val catalogListing: Boolean? = null
)

data class Address (
    val stateID: StateID? = null,
    val stateName: StateName? = null,
    val cityID: String="",
    val cityName: String=""
)

enum class StateID {
    CoAnt,
    CoCun,
    CoDc,
    CoRis,
    CoVac
}

enum class StateName {
    Antioquia,
    BogotáDC,
    Cundinamarca,
    Risaralda,
    ValleDelCauca
}

data class Attribute (
    val valueStruct: Struct? = null,
    val source: Long=0L,
    val id: String="",
    val valueID: String="",
    val values: List<AttributeValue>? = null,
    val attributeGroupID: AttributeGroupID? = null,
    val attributeGroupName: AttributeGroupName? = null,
    val name: String="",
    val valueName: String=""
)

enum class AttributeGroupID {
    Others
}

enum class AttributeGroupName {
    Otros
}

data class Struct (
    val number: Double? = null,
    val unit: Unit? = null
)

enum class Unit {
    CM,
    G,
    M,
    Mm
}

data class AttributeValue (
    val id: String="",
    val name: String="",
    val struct: Struct? = null,
    val source: Long=0L
)

enum class BuyingMode {
    BuyItNow
}

enum class Condition {
    New
}

enum class CurrencyID {
    Cop
}

data class DifferentialPricing (
    val id: Long=0L
)

data class Installments (
    val quantity: Long=0L,
    val amount: Double? = null,
    val rate: Long=0L,
    val currencyID: CurrencyID? = null
)

enum class ListingTypeID {
    GoldPro,
    GoldSpecial
}

data class Prices (
    val id: String="",
    val prices: List<Price>? = null,
    val presentation: Presentation? = null,
    val paymentMethodPrices: List<Any?>? = null,
    val referencePrices: List<Any?>? = null,
    val purchaseDiscounts: List<Any?>? = null
)

data class Presentation (
    val displayCurrency: CurrencyID? = null
)

data class Price (
    val id: String="",
    val type: Type? = null,
    val amount: Long=0L,
    val regularAmount: Long=0L,
    val currencyID: CurrencyID? = null,
    val lastUpdated: String="",
    val conditions: Conditions? = null,
    val exchangeRateContext: ExchangeRateContext? = null,
    val metadata: Metadata? = null
)

data class Conditions (
    val contextRestrictions: List<String>? = null,
    val startTime: String="",
    val endTime: String="",
    val eligible: Boolean? = null
)

enum class ExchangeRateContext {
    Default
}

data class Metadata (
    val campaignID: String="",
    val promotionID: String="",
    val promotionType: PromotionType? = null,
    val discountMeliAmount: Long=0L,
    val campaignDiscountPercentage: Long=0L,
    val campaignEndDate: String="",
    val orderItemPrice: Long=0L
)

enum class PromotionType {
    Campaign,
    Custom,
    Lightning,
    MarketplaceCampaign
}

enum class Type {
    Promotion,
    Standard
}

data class Seller (
    val id: Long=0L,
    val permalink: Any? = null,
    val registrationDate: Any? = null,
    val carDealer: Boolean? = null,
    val realEstateAgency: Boolean? = null,
    val tags: Any? = null
)

data class SellerAddress (
    val id: String="",
    val comment: String="",
    val addressLine: String="",
    val zipCode: String="",
    val country: Sort? = null,
    val state: Sort? = null,
    val city: Sort? = null,
    val latitude: String="",
    val longitude: String=""
)

data class Shipping (
    val freeShipping: Boolean? = null,
    val mode: Mode? = null,
    val tags: List<ShippingTag>? = null,
    val logisticType: LogisticType? = null,
    val storePickUp: Boolean? = null
)

enum class LogisticType {
    CrossDocking,
    Custom,
    Fulfillment,
    XdDropOff
}

enum class Mode {
    Custom,
    Me2
}

enum class ShippingTag {
    FSThresholdMcoChangeApr2021,
    FSThresholdMcoChangeJul2021,
    Fulfillment,
    IsFlammable,
    MandatoryFreeShipping,
    SelfServiceIn,
    SelfServiceOut
}


enum class ResultTag {
    BestSellerCandidate,
    CartEligible,
    CatalogListingEligible,
    DraggedBidsAndVisits,
    GoodQualityPicture,
    GoodQualityThumbnail,
    ImmediatePayment,
    IncompleteTechnicalSpecs,
    LoyaltyDiscountEligible,
    ShippingGuaranteed
}
