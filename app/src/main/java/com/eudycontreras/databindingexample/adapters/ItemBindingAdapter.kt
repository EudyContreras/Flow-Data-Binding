package com.eudycontreras.databindingexample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.eudycontreras.databindingexample.BR
import com.eudycontreras.databindingexample.ItemBinding
import com.eudycontreras.databindingexample.helpers.diffing.DiffComparable

open class ItemBindingAdapter<T : DiffComparable>(
    protected open val items: List<T>,
    private val itemBinding: ItemBinding<T>
) : RecyclerView.Adapter<ItemBindingAdapter.ItemViewHolder<T>>() {

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return itemBinding.invoke(items[position])
    }

    override fun onBindViewHolder(holder: ItemViewHolder<T>, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        @LayoutRes viewType: Int
    ): ItemViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(binding = DataBindingUtil.inflate(inflater, viewType, parent, false))
    }

    class ItemViewHolder<T : DiffComparable>(
        private val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: T?) {
            binding.setVariable(BR.viewModel, data)
            binding.executePendingBindings()
        }
    }
}