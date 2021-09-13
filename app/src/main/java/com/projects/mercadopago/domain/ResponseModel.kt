package com.projects.mercadopago.domain

import com.squareup.moshi.Json


data class ResponseModel (
    @Json(name="site_id")
    val siteID: String? = null,

    @Json(name="country_default_time_zone")
    val countryDefaultTimeZone: String? = null,

    val query: String? = null,
    val paging: Paging? = null,
    @Json(name = "results")
    val products: List<ProductModel>? = null,
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
    val results: Double? = null
)


data class Sort (
    val id: String? = null,
    val name: String? = null
)


data class Paging (
    val total: Double? = null,

    @Json(name="primary_results")
    val primaryResults: Double? = null,

    val offset: Double? = null,
    val limit: Double? = null
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

    val source: Double? = null,
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
    val source: Double? = null
)




data class DifferentialPricing (
    val id: Double? = null
)


data class Installments (
    val quantity: Double? = null,
    val amount: Double? = null,
    val rate: Double? = null,

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
    val referencePrices: List<Any>? = null,

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
    val amount: Double? = null,

    @Json(name="regular_amount")
    val regularAmount: Double? = null,

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
    val discountMeliAmount: Double? = null,

    @Json(name="campaign_discount_percentage")
    val campaignDiscountPercentage: Float? = null,

    @Json(name="campaign_end_date")
    val campaignEndDate: String? = null,

    @Json(name="order_item_price")
    val orderItemPrice: Double? = null
)


data class Seller (
    val id: Double? = null,
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

