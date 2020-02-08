package com.jetchoco.ithelparchitecture.util

import androidx.lifecycle.Observer

// ref: https://gist.github.com/JoseAlcerreca/e0bba240d9b3cffa258777f12e5c0ae9
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}