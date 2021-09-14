package com.projects.mercadopago.domain

import com.projects.mercadopago.database.DatabaseProduct

/**
 * Map DatabaseProduct to domain objects
 * */

fun List<DatabaseProduct>.asDomainModel():List<Product>{
    return map {
        Product(
            productID = it.productID,
            title = it.title,
            price = it.price,
            image = it.thumbnail
        )
    }
}

fun DatabaseProduct.asDomainModel():Product{
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

fun ResponseModel.asDomainModel():List<Product>{
    return results.map {
        Product(productID = it.id,
            title = it.title,
            price = it.price,
            image = it.thumbnail)
    }
}

fun ResultModel.asDomainModel():Product{
    return Product(
        productID = id,
        title = title,
        price = price,
        image = thumbnail
    )
}

/**
 * Convert Network results to Database objects
 * */

fun ResponseModel.asDatabaseModel():List<DatabaseProduct>{
    return results.map {
        DatabaseProduct(
            productID = it.id,
            title = it.title,
            price = it.price,
            basePrice = it.originalPrice?:it.price,
            stopTime = it.stopTime,
            condition = it.condition,
            permalink = it.permalink,
            thumbnail = it.thumbnail,
        )
    }
}

fun ResultModel.asDatabaseModel():DatabaseProduct{
    return DatabaseProduct(
        productID = id,
        title = title,
        price = price,
        basePrice = originalPrice?:price,
        stopTime = stopTime,
        condition = condition,
        permalink = permalink,
        thumbnail = thumbnail,
    )
}