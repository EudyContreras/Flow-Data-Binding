package com.eudycontreras.databindingexample.helpers.diffing

interface DiffComparable {
    val id: String
    fun haveSameContent(other: DiffComparable): Boolean
}