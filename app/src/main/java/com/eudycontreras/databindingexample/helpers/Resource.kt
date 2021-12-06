package com.eudycontreras.databindingexample.helpers

import kotlin.Exception

sealed class Resource<T> {
    data class Success<T>(
        val result: T
    ) : Resource<T>()

    data class Loading<T>(
        val cache: T? = null
    ) : Resource<T>()

    data class Failure<T>(
        val code: Int = -1,
        val exception: Exception? = null,
        val onRetry: (() -> Unit)? = null
    ): Resource<T>()
}