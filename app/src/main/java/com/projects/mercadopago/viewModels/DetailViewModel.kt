package com.projects.mercadopago.viewModels

import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.domain.ProductDetail
import com.projects.mercadopago.data.network.networkModels.Description
import com.projects.mercadopago.data.repository.ProductsRepository
import com.projects.mercadopago.data.repository.ResultMercadoPago
import com.projects.mercadopago.data.repository.succeeded
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Error

class DetailViewModel(private val repository: ProductsRepository) : ViewModel() {


    private val _productID = MutableLiveData("")
    private val _productDetail = MutableLiveData<Product?>()
    private val _description = MutableLiveData("")

    val productID: LiveData<String>
        get() = _productID
    val productDetail: LiveData<Product?>
        get() = _productDetail
    val description: LiveData<String?>
        get() = _description


    fun getProductDetails(productID: String) {
        _productID.value = productID
        viewModelScope.launch {
            val product = repository.getProduct(_productID.value ?: "")
            if (product is ResultMercadoPago.Success) {
                _productDetail.value = product.data
                Timber.i("product ${product.data}")
                val description = repository.getProductDescription(productID)
                if (description is ResultMercadoPago.Success)
                    _description.value = description.data
                else if (description is ResultMercadoPago.Error)
                    Timber.i("Error \n ${description.exception}")
            } else if (product is ResultMercadoPago.Error)
                Timber.i("Error \n ${product.exception}")
        }
    }


}