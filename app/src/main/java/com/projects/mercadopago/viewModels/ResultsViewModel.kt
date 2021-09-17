package com.projects.mercadopago.viewModels

import androidx.lifecycle.*
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.network.MercadoApiStatus
import com.projects.mercadopago.data.repository.ProductsRepository
import com.projects.mercadopago.data.repository.ResultMercadoPago
import com.projects.mercadopago.data.repository.ResultMercadoPago.Error
import com.projects.mercadopago.data.repository.ResultMercadoPago.Success
import kotlinx.coroutines.launch
import timber.log.Timber

class ResultsViewModel(private val repository: ProductsRepository) : ViewModel() {

    private val _status = MutableLiveData<MercadoApiStatus>()
    private val _query = MutableLiveData<String>()
    private val _products = MutableLiveData<List<Product>?>()
    val result = repository.observeProducts()
    private val _isEmptySearch = Transformations.switchMap(products) { list ->
        Transformations.map(_status) { status ->
            list?.isEmpty() == true && status == MercadoApiStatus.DONE
        }
    }

    val isEmptySearch: LiveData<Boolean>
        get() = _isEmptySearch
    val products: LiveData<List<Product>?>
        get() = _products

    val status: LiveData<MercadoApiStatus>
        get() = _status

    val query: LiveData<String>
        get() = _query

    init {
        resetViewModel()
    }

    fun resetViewModel() {
        viewModelScope.launch {
            repository.deleteAllProducts()
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


    fun refreshProducts(productsResult: ResultMercadoPago<List<Product>>) {
        if (_status.value != MercadoApiStatus.ERROR)
            if (productsResult is Success) {
                _products.value = productsResult.data
            } else if( productsResult is Error){
                _products.value = null
                _status.value = MercadoApiStatus.ERROR
                Timber.e("Error getting data from database \n ${productsResult.exception}")
            }
    }

    private fun getQueryProducts(query: String) {
        _status.value = MercadoApiStatus.LOADING
        viewModelScope.launch {
            val result = repository.getProducts(query)
            if (result is Success)
                _status.value = MercadoApiStatus.DONE
            else if (result is Error) {
                _status.value = MercadoApiStatus.ERROR
                Timber.e("Error getting data from service \n ${result.exception}")
            }
        }
    }

}