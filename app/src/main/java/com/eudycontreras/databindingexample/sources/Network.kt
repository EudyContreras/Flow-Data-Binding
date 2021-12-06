package com.eudycontreras.databindingexample.sources

import com.eudycontreras.databindingexample.datamodels.DemoRequest
import com.eudycontreras.databindingexample.datamodels.DemoResponse
import com.eudycontreras.databindingexample.datamodels.MockedData
import com.eudycontreras.databindingexample.extensions.safeRelease
import com.eudycontreras.databindingexample.helpers.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay as networkDelay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.sync.Semaphore

class Network(
    private val scope: CoroutineScope
): Source<DemoResponse, DemoRequest> {
    override suspend fun getData(request: DemoRequest): Resource<DemoResponse> {
        networkDelay(1000)
        return Resource.Success(
            result = DemoResponse(
                when (request) {
                    is DemoRequest.A -> MockedData.dataA
                    is DemoRequest.B -> MockedData.dataB
                }
            )
        )
    }

    override fun getDataFlow(
        request: DemoRequest
    ): StateFlow<Resource<DemoResponse>> {
        val semaphore = Semaphore(permits = 1, acquiredPermits = 1)
        val failure = Resource.Failure<DemoResponse>(
            code = 430,
            onRetry = { semaphore.safeRelease() }
        )
        return flow {
            networkDelay(2000)
            emit(failure)
            while (true) {
                semaphore.acquire()
                emit(Resource.Loading())
                emit(getData(request))
            }
        }.stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(500),
            initialValue = Resource.Loading()
        )
    }
}