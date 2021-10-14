package com.projects.mercadopago.viewModels

import androidx.lifecycle.*
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.network.MercadoApiStatus
import com.projects.mercadopago.data.repository.IProductsRepository
import com.projects.mercadopago.data.repository.ResultMercadoPago
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: IProductsRepository,
    savedStateHandle: SavedStateHandle?,
) : ViewModel() {


    private val _productID = MutableLiveData("")
    private val _productDetail = MutableLiveData<Product?>()
    private val _description = MutableLiveData("")
    private val _status = MutableLiveData<MercadoApiStatus>()
    var isLoading = _status.map {
        it == MercadoApiStatus.DONE
    }

    val productID: LiveData<String>
        get() = _productID
    val productDetail: LiveData<Product?>
        get() = _productDetail
    val description: LiveData<String?>
        get() = _description
    val status: LiveData<MercadoApiStatus>
        get() = _status

    init {
        val productID = savedStateHandle?.get<String>("product_id")
        _productID.value = productID
        getProductDetails(productID)
    }


    private fun getProductDetails(productID: String?) {
        if (!productID.isNullOrBlank()) {
            _status.value = MercadoApiStatus.LOADING
            viewModelScope.launch {
                val product = repository.getProduct(_productID.value ?: "")
                if (product is ResultMercadoPago.Success) {
                    _productDetail.value = product.data
                    val description = repository.getProductDescription(productID)
                    if (description is ResultMercadoPago.Success)
                        _description.value = description.data
                    else if (description is ResultMercadoPago.Error)
                        Timber.e("Error \n ${description.exception}")
                    _status.value = MercadoApiStatus.DONE
                } else if (product is ResultMercadoPago.Error) {
                    Timber.e("Error \n ${product.exception}")
                    _status.value = MercadoApiStatus.ERROR
                }

            }
        }
    }


}