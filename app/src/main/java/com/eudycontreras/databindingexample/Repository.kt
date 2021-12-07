package com.eudycontreras.databindingexample

import com.eudycontreras.databindingexample.helpers.Resource
import com.eudycontreras.databindingexample.datamodels.DemoRequest
import com.eudycontreras.databindingexample.datamodels.DemoResponse
import com.eudycontreras.databindingexample.datamodels.MockedData
import com.eudycontreras.databindingexample.sources.Database
import com.eudycontreras.databindingexample.sources.Network
import com.eudycontreras.databindingexample.sources.SourceHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Repository(scope: CoroutineScope) {

    private val sourceHandler: SourceHandler<DemoResponse, DemoRequest> = SourceHandler(
        localSource = Database(scope = scope),
        remoteSource = Network(scope = scope)
    )

    fun getDemoDataA(
        request: DemoRequest.A
    ): StateFlow<Resource<DemoResponse>> = sourceHandler.getDataFlow(request)

    fun getDemoDataB(
        request: DemoRequest.B
    ): StateFlow<Resource<DemoResponse>> = sourceHandler.getDataFlow(request)

    fun getDemoData(): StateFlow<Resource<List<DemoResponse>>> {
        return MutableStateFlow(
            Resource.Success((0..10).map {
                DemoResponse(
                    when {
                        it % 2 == 0 -> MockedData.dataA
                        else -> MockedData.dataB
                    }
                )
            })
        )
    }
}