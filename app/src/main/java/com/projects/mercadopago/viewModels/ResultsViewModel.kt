package com.projects.mercadopago.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.mercadopago.domain.ProductModel
import com.projects.mercadopago.network.MercadoApiStatus
import com.projects.mercadopago.network.MercadoPagoNetwork
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class ResultsViewModel(query: String) : ViewModel() {

    private val _products = MutableLiveData<List<ProductModel>>()
    private val _status = MutableLiveData<MercadoApiStatus>()

    val products: LiveData<List<ProductModel>>
        get() = _products

    val status: LiveData<MercadoApiStatus>
        get() = _status

    init {
        if (query.isBlank())
            Timber.i("Need to controlled empty or blank")
        getQueryProducts(query)
    }

    private fun getQueryProducts(query: String) {
        viewModelScope.launch {
            _status.value = MercadoApiStatus.LOADING
            delay(2000)
            try {
                val products =
                    MercadoPagoNetwork.retrofitService.getProductsByQuery(query = query).products
                if (products.isNullOrEmpty()) {
                    _status.value=MercadoApiStatus.EMPTY_SEARCH
                    return@launch
                }
                _products.value = products
                _status.value = MercadoApiStatus.DONE
            } catch (error: Exception) {
                _products.value = ArrayList()
                _status.value = MercadoApiStatus.ERROR
                Timber.i("Error getting data \n $error")
            }
        }
    }
}