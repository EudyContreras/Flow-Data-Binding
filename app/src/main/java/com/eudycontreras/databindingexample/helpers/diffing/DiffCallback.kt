package com.eudycontreras.databindingexample.helpers.diffing

import androidx.recyclerview.widget.DiffUtil

class DiffCallback<T: DiffComparable>(
    private var oldItems: List<T>,
    private var newItems: List<T>
): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].id == newItems[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].haveSameContent(newItems[newItemPosition])
    }

    override fun getOldListSize() = oldItems.size
    override fun getNewListSize() = newItems.size
}