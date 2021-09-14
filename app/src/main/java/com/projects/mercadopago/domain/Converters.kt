package com.projects.mercadopago.domain

import com.projects.mercadopago.database.DatabaseProduct

/**
 * Map DatabaseProduct to domain entities
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

fun DatabaseProduct.asDomain():Product{
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

fun ResultModel.asDomain():Product{
    return Product(
        productID = id,
        title = title,
        price = price,
        image = thumbnail
    )
}