package com.eudycontreras.databindingexample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eudycontreras.databindingexample.ItemBinding
import com.eudycontreras.databindingexample.R
import com.eudycontreras.databindingexample.datamodels.DemoData
import com.eudycontreras.databindingexample.datamodels.DemoRequest
import com.eudycontreras.databindingexample.extensions.stateMap
import com.eudycontreras.databindingexample.helpers.Resource
import com.eudycontreras.databindingexample.helpers.UIState
import com.eudycontreras.databindingexample.Repository
import com.eudycontreras.databindingexample.adapters.ItemBindingAdapterMutable
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class DemoViewModelCollection : ViewModel() {

    private val repository: Repository = Repository(viewModelScope)

    /**
     * Hot flow of demo data objects that can be bound to
     * to a ItemBindingAdapter. When the value of this flow changes
     * the recycler will reflect those changes
     */
    val items: StateFlow<List<DemoData>> = repository.getDemoData().stateMap(
        scope = viewModelScope
    ) { resource ->
        when (resource) {
            is Resource.Success -> resource.result.map { it.data }
            else -> emptyList()
        }
    }

    /**
     * With this this item binder we can bind any data type
     * to any layout with the matching signature.
     */
    val itemBinding: ItemBinding<DemoData> = { data ->
        when (data) {
            is DemoData.TypeA -> R.layout.layout_item_a
            is DemoData.TypeB -> R.layout.layout_item_b
        }
    }

    /**
     * We can bind this adapter to any recycler.
     */
    val adapter: ItemBindingAdapterMutable<DemoData> = ItemBindingAdapterMutable(
        initialItems = items.value,
        itemBinding = itemBinding
    )

    init {
        viewModelScope.launch {
            items.collect {
                adapter.updateData(it)
            }
        }
    }
}