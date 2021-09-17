package com.projects.mercadopago.viewModels.viewModelsFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projects.mercadopago.viewModels.SearchViewModel

@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory:ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        SearchViewModel() as T
}