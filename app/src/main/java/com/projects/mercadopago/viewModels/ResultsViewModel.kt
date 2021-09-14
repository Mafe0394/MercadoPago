package com.projects.mercadopago.viewModels

import androidx.lifecycle.*
import com.projects.mercadopago.domain.Product
import com.projects.mercadopago.domain.ResponseModel
import com.projects.mercadopago.domain.ResultModel
import com.projects.mercadopago.network.MercadoApiStatus
import com.projects.mercadopago.network.MercadoPagoNetwork
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class ResultsViewModel( _query: String) : ViewModel() {

    private val _products = MutableLiveData<List<ResultModel>>()
    private val _status = MutableLiveData<MercadoApiStatus>()

    val products: LiveData<List<ResultModel>>
        get() = _products

    val status: LiveData<MercadoApiStatus>
        get() = _status

    val query=_query



    init {
        if (_query.isBlank())
            Timber.i("Need to controlled empty or blank")
        getQueryProducts(_query)
    }

    private fun getQueryProducts(query: String) {
        viewModelScope.launch {
            _status.value = MercadoApiStatus.LOADING
            delay(2000)
            try {
                val products =
                    MercadoPagoNetwork.retrofitService.getProductsByQuery(query = query).results
                if (products.isNullOrEmpty()) {
                    _status.value=MercadoApiStatus.EMPTY_SEARCH
                    return@launch
                }
                _products.value = products
                _status.value = MercadoApiStatus.DONE
            } catch (error: Exception) {
                _products.value = ArrayList()
                _status.value = MercadoApiStatus.ERROR
                Timber.e("Error getting data \n $error")
            }
        }
    }
}