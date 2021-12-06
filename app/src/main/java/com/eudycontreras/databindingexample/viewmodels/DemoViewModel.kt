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
import kotlinx.coroutines.flow.StateFlow

internal class DemoViewModel : ViewModel() {

    private val repository: Repository = Repository(viewModelScope)

    /**
     * Flow which updates our UI state and maps to our repository
     */

    val uiState: StateFlow<UIState<DemoData>> = repository.getDemoDataA(
        request = DemoRequest.A
    ).stateMap(scope = viewModelScope) {
        when (it) {
            is Resource.Loading -> UIState.Loading()
            is Resource.Success -> UIState.Expected(it.result.data)
            is Resource.Failure -> getUIErrorState(it.code, it.onRetry)
        }
    }

    /**
     * Imagine our success state can be anything
     * in our case we have polymorphic data (A and B)
     */
    private val expectedStateBinding: ItemBinding<DemoData> = { data ->
        when (data) {
            is DemoData.TypeA -> R.layout.layout_item_a
            is DemoData.TypeB -> R.layout.layout_item_b
        }
    }

    /**
     * We decide which view we want to bind based
     * on our UI state
     */
    val stateBinding: ItemBinding<UIState<DemoData>> = { state ->
        when (state) {
            is UIState.Expected<DemoData> -> expectedStateBinding(state.data)
            is UIState.Loading -> R.layout.layout_state_loading
            is UIState.Error -> R.layout.layout_state_error
        }
    }

    /**
     * Example of how to deal with errors
     */
    private fun getUIErrorState(
        code: Int,
        onRetry: (() -> Unit)?
    ): UIState.Error = when (code) {
        in ErrorCodes.ERROR_TYPE_A,
        in ErrorCodes.ERROR_TYPE_B,
        in ErrorCodes.ERROR_TYPE_C -> UIState.Error(
            title = R.string.general_error_title,
            body = R.string.general_error_body,
            onRetry = onRetry
        )
        else -> TODO()
    }

    object ErrorCodes {
        val ERROR_TYPE_A = 300 until 400 // Redirection errors
        val ERROR_TYPE_B = 400 until 500 // Client errors
        val ERROR_TYPE_C = 500 until 600 // Server errors
    }
}