package com.eudycontreras.databindingexample.sources

import com.eudycontreras.databindingexample.helpers.Resource
import kotlinx.coroutines.flow.StateFlow

/**
 * We handle where to get the resource from (locally or remotely)
 */
class SourceHandler<T: Any, R: Any>(
    val localSource: Source<T, R>,
    val remoteSource: Source<T, R>
): Source<T, R> {

    override suspend fun getData(request: R): Resource<T> {
        return localSource.getData(request)
    }

    override fun getDataFlow(request: R): StateFlow<Resource<T>> {
        return remoteSource.getDataFlow(request)
    }
}