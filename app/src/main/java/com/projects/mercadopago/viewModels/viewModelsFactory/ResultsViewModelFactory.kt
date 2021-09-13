package com.projects.mercadopago.viewModels.viewModelsFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projects.mercadopago.viewModels.ResultsViewModel

@Suppress("UNCHECKED_CAST")
class ResultsViewModelFactory(val query:String) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ResultsViewModel(query) as T
}