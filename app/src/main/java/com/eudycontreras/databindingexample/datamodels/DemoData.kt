package com.eudycontreras.databindingexample.datamodels

import com.eudycontreras.databindingexample.helpers.diffing.DiffComparable

sealed class DemoData: DiffComparable {
    data class TypeA(
        override val id: String,
        val text: String,
        val imageUrl: String
    ) : DemoData()

    data class TypeB(
        override val id: String,
        val textOne: String,
        val textTwo: String,
        val imageUrl: String
    ) : DemoData()

    override fun haveSameContent(other: DiffComparable): Boolean {
       return this == other
    }
}
