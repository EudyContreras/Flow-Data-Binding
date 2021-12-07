package com.eudycontreras.databindingexample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.eudycontreras.databindingexample.Repository
import com.eudycontreras.databindingexample.helpers.Resource
import com.eudycontreras.databindingexample.datamodels.DemoData
import com.eudycontreras.databindingexample.datamodels.DemoRequest
import kotlinx.coroutines.flow.map

internal class DemoViewModelSimple1 : ViewModel() {

    /**
     * This repository can be injected
     */
    private val repository: Repository = Repository(viewModelScope)

    /**
     * We can bind demo data to "layout_item_b.xml"
     */
    val demoData: LiveData<DemoData.TypeA?> = repository.getDemoDataA(DemoRequest.A).map {
        when (it) {
            is Resource.Success -> it.result.data as DemoData.TypeA
            else -> null
        }
    }.asLiveData(
        context = viewModelScope.coroutineContext
    )
}