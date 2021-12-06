package com.eudycontreras.databindingexample.sources

import com.eudycontreras.databindingexample.helpers.Resource
import kotlinx.coroutines.flow.StateFlow

/**
 * Common interface for both local
 * and remote sources.
 */
interface Source<T: Any, R: Any> {
    suspend fun getData(request: R): Resource<T>
    fun getDataFlow(request: R): StateFlow<Resource<T>>
}