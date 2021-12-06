package com.eudycontreras.databindingexample.extensions

import android.content.res.Resources
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Semaphore

/**
 * Use the float as density independent pixels and return the pixel value
 */
val Int.dp: Float
    get() = this * Resources.getSystem().displayMetrics.density

val Float.dp: Float
    get() = this * Resources.getSystem().displayMetrics.density

fun Semaphore.safeRelease() {
    if (availablePermits <= 0) {
        release()
    }
}

inline fun <T, R> StateFlow<T>.stateMap(
    scope: CoroutineScope,
    started: SharingStarted = SharingStarted.WhileSubscribed(5000),
    crossinline transform: (value: T) -> R
): StateFlow<R> {
    return this.map {
        transform(it)
    }.stateIn(
        scope = scope,
        started = started,
        initialValue = transform(value)
    )
}