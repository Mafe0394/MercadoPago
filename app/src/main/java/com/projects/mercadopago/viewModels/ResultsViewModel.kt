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

    private val _status = MutableLiveData<MercadoApiStatus>()
    private val _query = MutableLiveData<String>()
    private val _products: LiveData<List<Product>> = Transformations.switchMap(_query) {
        if (it.isNullOrBlank())
            MutableLiveData<List<Product>>(null)
        else
            repository.products1
    }
    private val _products1: LiveData<List<Product>> = Transformations.switchMap(_query) {
            repository.products
    }
    private val _isEmptySearch = Transformations.switchMap(_products1) {list->
        Transformations.map(_status){status->
            list.isNullOrEmpty()&&status==MercadoApiStatus.DONE
        }
    }

    val isEmptySearch: LiveData<Boolean>
        get() = _isEmptySearch
    val products: LiveData<List<Product>>
        get() = repository.products

    val status: LiveData<MercadoApiStatus>
        get() = _status

    val query: LiveData<String>
        get() = _query


    fun resetViewModel() {
        viewModelScope.launch {
            repository.deleteSearch()
        }
    }

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
                repository.getProductsQuery1(query)
                _status.value = MercadoApiStatus.DONE
            } catch (error: Exception) {
                _status.value = MercadoApiStatus.ERROR
                Timber.e("Error getting data \n $error")
            }
        }
    }

}