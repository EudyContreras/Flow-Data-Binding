package com.eudycontreras.databindingexample.extensions

import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eudycontreras.databindingexample.BR
import com.eudycontreras.databindingexample.ItemBinding
import com.eudycontreras.databindingexample.adapters.ItemBindingAdapter
import com.eudycontreras.databindingexample.adapters.ItemBindingAdapterMutable
import com.eudycontreras.databindingexample.helpers.UIState
import com.eudycontreras.databindingexample.helpers.diffing.DiffComparable

@BindingAdapter(value = ["android:layout_width", "android:layout_height"], requireAll = true)
fun ProgressBar.size(width: Float, height: Float) {
    layoutParams = FrameLayout.LayoutParams(width.toInt(), height.toInt(), Gravity.CENTER)
    requestLayout()
}

@BindingAdapter(value = ["android:text"], requireAll = true)
fun TextView.text(@StringRes resId: Int) {
    this.setText(resId)
}

@BindingAdapter(value = ["imageUrl", "placeholder"])
fun ImageView.loadImage(imageUrl: String?, placeholder: Drawable) {
    if (imageUrl == null) return
    Glide.with(context)
        .load(imageUrl)
        .fallback(placeholder)
        .placeholder(placeholder)
        .into(this)
}

/**
 * Example for a Binding Collection Adapter
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter(value = ["items", "itemBinding"], requireAll = true)
fun <T : DiffComparable> RecyclerView.setItemData(
    items: List<T>,
    itemBinding: ItemBinding<T>
) {
    if (adapter == null) {
        adapter = ItemBindingAdapterMutable(items, itemBinding)
        return
    }
    with(adapter as ItemBindingAdapterMutable<T>) {
        updateData(items)
    }
}

/**
 * Example for a Binding Collection Adapter
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter(value = ["adapter"], requireAll = true)
fun <T : DiffComparable> RecyclerView.setItemData(
    bindingAdapter: ItemBindingAdapter<T>
) {
    if (adapter == null) {
        adapter = bindingAdapter
    }
}

/**
 * Better yet we can use a ViewSwitcher
 */
@BindingAdapter(value = ["state", "stateBindings"], requireAll = true)
fun <T> FrameLayout.addBindings(
    state: UIState<T>,
    stateBindings: ItemBinding<UIState<T>>
) {
    removeAllViews()
    val parent = this
    val attach = true
    DataBindingUtil.inflate<ViewDataBinding>(
        LayoutInflater.from(context),
        stateBindings.invoke(state),
        parent,
        attach
    ).apply {
        when (state) {
            is UIState.Expected -> setVariable(BR.viewModel, state.data)
            else -> setVariable(BR.viewModel, state)
        }
    }
}