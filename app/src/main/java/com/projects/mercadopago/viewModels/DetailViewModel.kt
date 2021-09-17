package com.projects.mercadopago.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {
    fun getProductDetails(productID: String) {
        _productID.value=productID

    }

    private val _productID = MutableLiveData("")

    val productID: LiveData<String>
        get() = _productID


}