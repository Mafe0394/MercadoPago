package com.projects.mercadopago.viewModels

import androidx.lifecycle.*
import com.projects.mercadopago.domain.Product
import com.projects.mercadopago.domain.ResponseModel
import com.projects.mercadopago.domain.ResultModel
import com.projects.mercadopago.network.MercadoApiStatus
import com.projects.mercadopago.network.MercadoPagoNetwork
import com.projects.mercadopago.repository.ProductsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class ResultsViewModel(private val repository: ProductsRepository) : ViewModel() {

    private val _query = MutableLiveData<String>()
    private val _products = MutableLiveData<List<Product>>()
    private val _status = MutableLiveData<MercadoApiStatus>()

    val products: LiveData<List<Product>>
        get() = _products

    val status: LiveData<MercadoApiStatus>
        get() = _status

    val query: LiveData<String>
        get() = _query

    val products1=repository.products1


    fun startQueryResults(query: String) {
        // If we're already loading or already loaded, return (might be a config change)
        if (_status.value == MercadoApiStatus.LOADING || query == _query.value) {
            return
        }
        _query.value = query
        getQueryProducts(query)
    }

    private fun getQueryProducts(query: String) {
        viewModelScope.launch {
            _status.value = MercadoApiStatus.LOADING
            delay(1000)
            try {
//                val products =
//                    MercadoPagoNetwork.retrofitService.getProductsByQuery(query = query).results
//                if (products.isNullOrEmpty()) {
//                    _status.value=MercadoApiStatus.EMPTY_SEARCH
//                    return@launch
//                }
//                _products.value = products
                repository.getProductsQuery(query)
                _products.value=repository.products1.value
                _status.value = MercadoApiStatus.DONE
            } catch (error: Exception) {
                _products.value = ArrayList()
                _status.value = MercadoApiStatus.ERROR
                Timber.e("Error getting data \n $error")
            }
        }
    }
}