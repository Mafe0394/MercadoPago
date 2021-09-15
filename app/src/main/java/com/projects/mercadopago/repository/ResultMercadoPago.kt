package com.projects.mercadopago.repository

import com.projects.mercadopago.repository.ResultMercadoPago.Success

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class ResultMercadoPago<out R> {

    data class Success<out T>(val data: T) : ResultMercadoPago<T>()
    data class Error(val exception: Exception) : ResultMercadoPago<Nothing>()
    object Loading : ResultMercadoPago<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val ResultMercadoPago<*>.succeeded
    get() = this is Success && data != null