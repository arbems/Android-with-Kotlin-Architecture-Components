package com.arbems.lifecycle

import android.content.Context
import android.location.Location
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

internal class MyLocationListener(
    private val context: Context,
    private val lifecycle: Lifecycle,
    private val tag: String,
    private val callback: (Location) -> Unit
) : LifecycleObserver {

    private var enabled = false

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.i(tag, "Lifecycle.Event.ON_CREATE - ${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        if (enabled) {
            // connect
        }

        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            Log.i(tag, "Lifecycle.Event.ON_START - ${lifecycle.currentState}")
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.i(tag, "Lifecycle.Event.ON_RESUME - ${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.i(tag, "Lifecycle.Event.ON_PAUSE - ${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        // disconnect if connected
        Log.i(tag, "Lifecycle.Event.ON_STOP - ${lifecycle.currentState}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.DESTROYED)) {
            Log.i(tag, "Lifecycle.Event.ON_DESTROY - ${lifecycle.currentState}")
        }
    }

    fun enable() {
        enabled = true
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            // connect if not connected
        }
    }
}
    