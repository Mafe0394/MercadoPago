package com.projects.mercadopago.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.domain.ProductDetail
import com.projects.mercadopago.data.repository.ProductsRepository
import com.projects.mercadopago.data.repository.ResultMercadoPago
import com.projects.mercadopago.data.repository.succeeded
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Error

class DetailViewModel(private val repository:ProductsRepository) : ViewModel() {


    private val _productID = MutableLiveData("")
    private val _productDetail=MutableLiveData<Product?>(null)

    val productID: LiveData<String>
        get() = _productID
    val productDetail:LiveData<Product?>
        get()=_productDetail

    fun getProductDetails(productID: String) {
        _productID.value=productID
        viewModelScope.launch {
            val product=repository.getProduct(_productID.value?:"")
            if (product is ResultMercadoPago.Success) {
                _productDetail.value = product.data
                Timber.i("product ${product.data}")
            }
            else if (product is ResultMercadoPago.Error)
                Timber.i("Error \n ${product.exception}")
        }
    }


}