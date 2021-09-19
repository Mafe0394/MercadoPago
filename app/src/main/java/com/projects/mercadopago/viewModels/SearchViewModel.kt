package com.projects.mercadopago.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.repository.ProductsRepository
import com.projects.mercadopago.data.repository.ResultMercadoPago
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchViewModel(private val repository: ProductsRepository) : ViewModel() {

    private val _query = MutableLiveData("")
    private val _isExpanded = MutableLiveData(false)
    private val _visitedProducts = MutableLiveData<List<Product>?>()
    var x=repository.observeVisitedProducts()

    val visitedProducts: LiveData<List<Product>?>
        get() = _visitedProducts

    val isExpanded: LiveData<Boolean>
        get() = _isExpanded

    val query: LiveData<String>
        get() = _query

    fun setQuery(queryText: String?) {
        _query.value = queryText
    }



    fun getVisitedProductsFromDatabase() {
        viewModelScope.launch {
            val products = repository.getVisitedProducts()
            if (products is ResultMercadoPago.Success) {
                _visitedProducts.value=products.data
                Timber.i("Success ${products.data}")
            }
            else if (products is ResultMercadoPago.Error)
                Timber.i("Error ${products.exception}")
        }
    }

    fun setPr(pro:List<Product>){
        _visitedProducts.value=pro
    }


    fun setIsExpanded(isExpanded: Boolean) {
        _isExpanded.value = isExpanded
    }

    fun resetQuery() {
        _query.value = null
    }


}