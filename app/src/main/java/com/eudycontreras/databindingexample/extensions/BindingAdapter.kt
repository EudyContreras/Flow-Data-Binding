package com.eudycontreras.databindingexample

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
import com.bumptech.glide.Glide
import com.eudycontreras.databindingexample.helpers.UIState

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