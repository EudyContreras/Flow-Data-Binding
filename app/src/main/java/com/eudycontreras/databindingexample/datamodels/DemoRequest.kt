package com.eudycontreras.databindingexample.datamodels

sealed class DemoRequest {
    object A : DemoRequest()
    object B : DemoRequest()
}

