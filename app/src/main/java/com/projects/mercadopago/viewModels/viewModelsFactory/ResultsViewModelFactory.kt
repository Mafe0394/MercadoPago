package com.projects.mercadopago.viewModels.viewModelsFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projects.mercadopago.data.repository.ProductsRepository
import com.projects.mercadopago.viewModels.ResultsViewModel

@Suppress("UNCHECKED_CAST")
class ResultsViewModelFactory(private val repository: ProductsRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ResultsViewModel(repository) as T
}