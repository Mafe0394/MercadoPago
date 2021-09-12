package com.projects.mercadopago.viewModels.viewModelsFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projects.mercadopago.viewModels.DetailViewModel

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory:ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel() as T
    }
}