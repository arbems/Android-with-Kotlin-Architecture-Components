package com.arbems.viewmodellifecycle

import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * ViewModel is a class what is responsible for preparing and managing the data for an Activity or a Fragment
 *
 * A ViewModel should never reference a view, Lifecycle, or class that can reference the context of the activity.
 * ViewModel's sole responsibility is to manage the data for the UI.
 */

class MainViewModel: ViewModel() {

    /**
     *  Properties and variables
     */
    private val _isSwitch = MutableLiveData<Boolean>()
    val isSwitch: LiveData<Boolean> get() = _isSwitch

    /**
     *  Init block
     */
    init {
        // init block is guaranteed to execute only once
        Log.i("ViewModel", "created!")
    }

    /**
     * Callback called when ViewModel is destroyed
     */
    override fun onCleared() {
        super.onCleared()
        Log.i("ViewModel", "destroyed!")
    }

    /** Methods for updating the UI **/
    fun change(isSwitch: Boolean) {
        _isSwitch.value = isSwitch
    }

}