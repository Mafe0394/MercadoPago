package com.projects.mercadopago.viewModels

import androidx.lifecycle.*
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.network.MercadoApiStatus
import com.projects.mercadopago.data.repository.ProductsRepository
import com.projects.mercadopago.data.repository.ResultMercadoPago
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchViewModel(private val repository: ProductsRepository) : ViewModel() {

    private val _query = MutableLiveData("")
    private val _isExpanded = MutableLiveData(false)
    private val _visitedProducts = MutableLiveData<List<Product>?>()
    private val _status = MutableLiveData<MercadoApiStatus>()
    var complete = repository.observeVisitedProducts().map {
        if (it is ResultMercadoPago.Success)
            it.data
        else
            ArrayList()
    }
    private val _emptyList = _visitedProducts.map {
        it.isNullOrEmpty()
    }
    var isLoading=_status.map {
        it==MercadoApiStatus.DONE
    }

    val query: LiveData<String>
        get() = _query
    val isExpanded: LiveData<Boolean>
        get() = _isExpanded
    val visitedProducts: LiveData<List<Product>?>
        get() = _visitedProducts
    val emptyList: LiveData<Boolean>
        get() = _emptyList
    val status: LiveData<MercadoApiStatus>
        get() = _status

    fun setQuery(queryText: String?) {
        _query.value = queryText
    }

    fun setIsExpanded(isExpanded: Boolean) {
        _isExpanded.value = isExpanded
    }

    fun resetQuery() {
        _query.value = null
    }

    fun getVisitedProductsFromDatabase() {
        _status.value=MercadoApiStatus.LOADING
        viewModelScope.launch {
            val products = repository.getVisitedProducts()
            if (products is ResultMercadoPago.Success) {
                _visitedProducts.value = products.data
                Timber.i("Success ${products.data}")
                _status.value=MercadoApiStatus.DONE
            } else if (products is ResultMercadoPago.Error) {
                _status.value=MercadoApiStatus.ERROR
                Timber.i("Error ${products.exception}")
            }
        }
    }

    fun deleteVisitedProducts() {
        viewModelScope.launch { repository.deleteVisitedProducts() }
    }

    fun setLitVisitedProducts(list: List<Product>?) {
        _visitedProducts.value=list
    }
}