package com.eudycontreras.databindingexample.helpers

import androidx.annotation.StringRes
import com.eudycontreras.databindingexample.extensions.dp

sealed class UIState<out T> {
    data class Expected<T>(
        val data: T
    ): UIState<T>()
    
    data class Loading(
        val size: Float = 50.dp
    ): UIState<Nothing>()

    data class Error(
        @StringRes val title: Int,
        @StringRes val body: Int,
        private val onRetry: (() -> Unit)?
    ): UIState<Nothing>() {
        val canRetry: Boolean = onRetry != null

        fun retry() {
            onRetry?.invoke()
        }
    }
}