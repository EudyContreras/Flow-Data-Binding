package com.eudycontreras.databindingexample.datamodels

sealed class DemoData {
    data class TypeA(
        val text: String,
        val imageUrl: String
    ) : DemoData()

    data class TypeB(
        val textOne: String,
        val textTwo: String,
        val imageUrl: String
    ) : DemoData()
}
