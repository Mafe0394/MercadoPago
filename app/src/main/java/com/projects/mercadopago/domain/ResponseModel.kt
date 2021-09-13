package com.projects.mercadopago.domain

import com.squareup.moshi.Json


data class ResponseModel (
    @Json(name="site_id")
    val siteID: String? = null,

    @Json(name="country_default_time_zone")
    val countryDefaultTimeZone: String? = null,

    val query: String? = null,
    val paging: Paging? = null,
    val results: List<ProductModel>? = null,
    val sort: Sort? = null,

    @Json(name="available_sorts")
    val availableSorts: List<Sort>? = null,

    val filters: List<AvailableFilter>? = null,

    @Json(name="available_filters")
    val availableFilters: List<AvailableFilter>? = null
)


data class AvailableFilter (
    val id: String? = null,
    val name: String? = null,
    val type: String? = null,
    val values: List<AvailableFilterValue>? = null
)


data class AvailableFilterValue (
    val id: String? = null,
    val name: String? = null,
    val results: Long? = null
)


data class Sort (
    val id: String? = null,
    val name: String? = null
)


data class Paging (
    val total: Long? = null,

    @Json(name="primary_results")
    val primaryResults: Long? = null,

    val offset: Long? = null,
    val limit: Long? = null
)


data class Address (
    @Json(name="state_id")
    val stateID: String? = null,

    @Json(name="state_name")
    val stateName: String? = null,

    @Json(name="city_id")
    val cityID: String? = null,

    @Json(name="city_name")
    val cityName: String? = null
)


data class Attribute (

    val source: Long? = null,
    val id: String? = null,

    @Json(name="value_id")
    val valueID: String? = null,

    val values: List<AttributeValue>? = null,

    @Json(name="attribute_group_id")
    val attributeGroupID: String? = null,

    @Json(name="attribute_group_name")
    val attributeGroupName: String? = null,

    val name: String? = null,

    @Json(name="value_name")
    val valueName: String? = null
)






data class AttributeValue (
    val id: String? = null,
    val name: String? = null,
    val source: Long? = null
)




data class DifferentialPricing (
    val id: Long? = null
)


data class Installments (
    val quantity: Long? = null,
    val amount: Double? = null,
    val rate: Long? = null,

    @Json(name="currency_id")
    val currencyID: String? = null
)


data class Prices (
    val id: String? = null,
    val prices: List<Price>? = null,
    val presentation: Presentation? = null,

    @Json(name="payment_method_prices")
    val paymentMethodPrices: List<String>? = null,

    @Json(name="reference_prices")
    val referencePrices: List<String>? = null,

    @Json(name="purchase_discounts")
    val purchaseDiscounts: List<String>? = null
)


data class Presentation (
    @Json(name="display_currency")
    val displayCurrency: String? = null
)


data class Price (
    val id: String? = null,
    val type: String? = null,
    val amount: Long? = null,

    @Json(name="regular_amount")
    val regularAmount: Long? = null,

    @Json(name="currency_id")
    val currencyID: String? = null,

    @Json(name="last_updated")
    val lastUpdated: String? = null,

    val conditions: Conditions? = null,

    @Json(name="exchange_rate_context")
    val exchangeRateContext: String? = null,

    val metadata: Metadata? = null
)


data class Conditions (
    @Json(name="context_restrictions")
    val contextRestrictions: List<String>? = null,

    @Json(name="start_time")
    val startTime: String? = null,

    @Json(name="end_time")
    val endTime: String? = null,

    val eligible: Boolean? = null
)


data class Metadata (
    @Json(name="campaign_id")
    val campaignID: String? = null,

    @Json(name="promotion_id")
    val promotionID: String? = null,

    @Json(name="promotion_type")
    val promotionType: String? = null,

    @Json(name="discount_meli_amount")
    val discountMeliAmount: Long? = null,

    @Json(name="campaign_discount_percentage")
    val campaignDiscountPercentage: Long? = null,

    @Json(name="campaign_end_date")
    val campaignEndDate: String? = null,

    @Json(name="order_item_price")
    val orderItemPrice: Long? = null
)


data class Seller (
    val id: Long? = null,
    val permalink: String? = null,

    @Json(name="registration_date")
    val registrationDate: String? = null,

    @Json(name="car_dealer")
    val carDealer: Boolean? = null,

    @Json(name="real_estate_agency")
    val realEstateAgency: Boolean? = null,

    val tags: String? = null
)


data class SellerAddress (
    val id: String? = null,
    val comment: String? = null,

    @Json(name="address_line")
    val addressLine: String? = null,

    @Json(name="zip_code")
    val zipCode: String? = null,

    val country: Sort? = null,
    val state: Sort? = null,
    val city: Sort? = null,
    val latitude: String? = null,
    val longitude: String? = null
)


data class Shipping (
    @Json(name="free_shipping")
    val freeShipping: Boolean? = null,

    val mode: String? = null,
    val tags: List<String>? = null,

    @Json(name="logistic_type")
    val logisticType: String? = null,

    @Json(name="store_pick_up")
    val storePickUp: Boolean? = null
)

