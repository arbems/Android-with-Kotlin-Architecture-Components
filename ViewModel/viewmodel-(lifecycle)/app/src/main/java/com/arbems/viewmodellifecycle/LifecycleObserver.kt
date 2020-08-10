package com.arbems.viewmodellifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LifecycleObserver(
    private val tag: String,
    private val lifecycle: Lifecycle

) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.i(tag, "Lifecycle.Event.ON_CREATE")
        Log.i(tag, "${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.i(tag, "Lifecycle.Event.ON_START")
        Log.i(tag, "${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.i(tag, "Lifecycle.Event.ON_RESUME")
        Log.i(tag, "${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.i(tag, "Lifecycle.Event.ON_PAUSE")
        Log.i(tag, "${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.i(tag, "Lifecycle.Event.ON_STOP")
        Log.i(tag, "${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.i(tag, "Lifecycle.Event.ON_DESTROY")
        Log.i(tag, "${lifecycle.currentState}")
    }
}