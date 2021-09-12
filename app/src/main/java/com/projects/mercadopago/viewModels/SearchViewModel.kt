package com.projects.mercadopago.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class SearchViewModel: ViewModel() {

    private val _query=MutableLiveData("")
    private val _isExpanded=MutableLiveData(false)

    val isExpanded:LiveData<Boolean>
    get() = _isExpanded

    val query:LiveData<String>
    get()=_query

    fun setQuery(queryText:String?){
        _query.value=queryText
    }

    fun setIsExpanded(isExpanded:Boolean){
        _isExpanded.value=isExpanded
    }

    fun resetQuery(){
        _query.value=null
    }


}