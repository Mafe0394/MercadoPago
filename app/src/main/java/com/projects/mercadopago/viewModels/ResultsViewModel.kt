package com.projects.mercadopago.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.mercadopago.domain.ProductModel
import com.projects.mercadopago.network.MercadoApiStatus
import com.projects.mercadopago.network.MercadoPagoNetwork
import kotlinx.coroutines.launch
import timber.log.Timber

class ResultsViewModel : ViewModel() {

    private val _products = MutableLiveData<List<ProductModel>>()
    private val _status = MutableLiveData<MercadoApiStatus>()

    val products: LiveData<List<ProductModel>>
        get() = _products

    val status: LiveData<MercadoApiStatus>
        get() = _status

    init {
        getQueryProducts()
    }

    private fun getQueryProducts() {
        viewModelScope.launch {
            _status.value = MercadoApiStatus.LOADING
            try {
                val result = MercadoPagoNetwork.retrofitService.getProducts()
                result.results?.get(0)?.thumbnail ="gato"
                Timber.i("Respuesta del servicio ${result.results?.size}")
                _products.value=result.results
//                _products.value = MercadoPagoNetwork.retrofitService.getProducts().results
                _status.value = MercadoApiStatus.DONE
            } catch (error: Exception) {
                _products.value = ArrayList()
                _status.value = MercadoApiStatus.ERROR
                Timber.i("Error getting data \n $error")
            }
        }
    }
}