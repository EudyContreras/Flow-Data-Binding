package com.eudycontreras.databindingexample.adapters

import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.eudycontreras.databindingexample.ItemBinding
import com.eudycontreras.databindingexample.helpers.diffing.DiffCallback
import com.eudycontreras.databindingexample.helpers.diffing.DiffComparable

class ItemBindingAdapterMutable<T : DiffComparable>(
    initialItems: List<T>,
    itemBinding: ItemBinding<T>
) : ItemBindingAdapter<T>(
    items = initialItems,
    itemBinding = itemBinding
) {
    override var items: List<T> = initialItems.toList()
    private val updateCallback: ListUpdateCallback = AdapterListUpdateCallback(this)

    fun updateData(newData: List<T>) {
        if (items != newData) {
            updateData(items, newData)
        }
    }

    private fun updateData(oldItems: List<T>, newItems: List<T>) {
        DiffUtil.calculateDiff(DiffCallback(oldItems, newItems)).run {
            items = newItems
            dispatchUpdatesTo(updateCallback)
        }
    }
}