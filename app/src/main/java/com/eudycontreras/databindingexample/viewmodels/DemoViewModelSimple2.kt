package com.eudycontreras.databindingexample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eudycontreras.databindingexample.Repository
import com.eudycontreras.databindingexample.helpers.Resource
import com.eudycontreras.databindingexample.datamodels.DemoData
import com.eudycontreras.databindingexample.datamodels.DemoRequest
import com.eudycontreras.databindingexample.extensions.stateMap
import kotlinx.coroutines.flow.StateFlow

/**
 * Example with StateFlow
 */
internal class DemoViewModelSimple2 : ViewModel() {

    /**
     * This repository can be injected
     */
    private val repository: Repository = Repository(viewModelScope)

    /**
     * We can bind demo data to "layout_item_a.xml"
     */
    val demoData: StateFlow<DemoData.TypeB?> = repository.getDemoDataB(DemoRequest.B).stateMap(
        scope = viewModelScope
    ) {
        when (it) {
            is Resource.Success -> it.result.data as DemoData.TypeB
            else -> null
        }
    }
}