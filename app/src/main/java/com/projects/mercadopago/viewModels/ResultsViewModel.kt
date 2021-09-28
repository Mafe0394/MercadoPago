package com.projects.mercadopago.viewModels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.network.MercadoApiStatus
import com.projects.mercadopago.data.repository.ProductsRepository
import com.projects.mercadopago.data.repository.ResultMercadoPago
import com.projects.mercadopago.data.repository.ResultMercadoPago.Error
import com.projects.mercadopago.data.repository.ResultMercadoPago.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val repository: ProductsRepository,
    savedStateHandle: SavedStateHandle?,
) : ViewModel() {

    private val _productsList = MutableLiveData<PagingData<Product>>()
    private val _status = MutableLiveData<MercadoApiStatus>()
    private val _query = MutableLiveData<String?>()
    private val _products = MutableLiveData<List<Product>?>()
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

    val query: LiveData<String?>
        get() = _query

    init {
        val query =savedStateHandle?.get<String>("queryString")
        _query.value = query
    }

    suspend fun getProductsList(): LiveData<PagingData<Product>> {
        val response = repository.getProductsPaging(_query.value).cachedIn(viewModelScope)
        _productsList.value = response.value
        return response
    }

}