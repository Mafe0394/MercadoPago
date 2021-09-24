package com.projects.mercadopago.data.domain

import com.projects.mercadopago.data.database.DatabaseProduct
import com.projects.mercadopago.data.network.networkModels.ProductDetailsResponse
import com.projects.mercadopago.data.network.networkModels.ResponseModel
import com.projects.mercadopago.data.network.networkModels.ResultModel

/**
 * Map DatabaseProduct to domain objects
 * */

fun List<DatabaseProduct>.asDomainModel(): List<Product> {
    return map {
        Product(
            productID = it.productID,
            title = it.title,
            price = it.price,
            image = it.thumbnail
        )
    }
}

fun DatabaseProduct.asDomainModel(): Product {
    return Product(
        productID = productID,
        title = title,
        price = price,
        image = thumbnail
    )
}

/**
 * Convert Network results to domain Objects
 * */

fun ResponseModel.asDomainModel(): List<Product> {
    return results.map {
        Product(productID = it.id,
            title = it.title,
            price = it.price,
            image = it.thumbnail)
    }
}

fun List<ResultModel>.asDomainModellist(): List<Product> {
    return map {
        Product(
            productID = it.id,
            title = it.title,
            price = it.price,
            image = it.thumbnail
        )
    }
}

/**
 * Convert Network results to Database objects
 * */

fun ResponseModel.asDatabaseModel(): List<DatabaseProduct> {
    return results.map {
        DatabaseProduct(
            productID = it.id,
            title = it.title,
            price = it.price,
            basePrice = it.originalPrice ?: it.price,
            stopTime = it.stopTime,
            condition = it.condition,
            permalink = it.permalink,
            thumbnail = it.thumbnail,
        )
    }
}

fun ResultModel.asDatabaseModel(): DatabaseProduct {
    return DatabaseProduct(
        productID = id,
        title = title,
        price = price,
        basePrice = originalPrice ?: price,
        stopTime = stopTime,
        condition = condition,
        permalink = permalink,
        thumbnail = thumbnail,
    )
}

fun ProductDetailsResponse.asDatabaseModel(): DatabaseProduct {
    return DatabaseProduct(
        productID = id ?: "Error",
        title = title ?: "Error",
        price = price ?: 0,
        basePrice = basePrice ?: 0,
        stopTime = stopTime ?: "Error",
        condition = condition ?: "Error",
        permalink = permalink ?: "Error",
        thumbnail = thumbnail ?: "Error",
    )
}

fun ProductDetailsResponse.asDomainModel(): Product {
    return Product(
        productID = id ?: "Error",
        title = title ?: "Error",
        price = price ?: 0,
        image = thumbnail ?: "Error",
        imagesUrls = pictures.map {
            it?.url ?: "Error"
        }
    )
}
