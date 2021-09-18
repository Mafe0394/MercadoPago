package com.projects.mercadopago.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeOnce(owner: LifecycleOwner, reactToChange: (T) -> Unit): Observer<T> {
    val wrappedObserver = object : Observer<T> {
        override fun onChanged(data: T) {
            reactToChange(data)
            removeObserver(this)
        }
    }

    observe(owner, wrappedObserver)
    return wrappedObserver
}
