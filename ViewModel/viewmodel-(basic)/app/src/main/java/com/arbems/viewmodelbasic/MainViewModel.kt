package com.arbems.viewmodelbasic

import androidx.lifecycle.ViewModel
import android.util.Log

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
    var varName = ""
    private lateinit var list: MutableList<String>

    /**
     *  Init block
     */
    init {
        // init block is guaranteed to execute only once
        Log.i("ViewModel", "ViewModel created!")
    }

    /**
     * Callback called when ViewModel is destroyed
     */
    override fun onCleared() {
        super.onCleared()
        Log.i("ViewModel", "ViewModel destroyed!")
    }

    /** Methods for updating the UI **/
    fun onSkip() { /*...*/ }
    fun onCorrect() { /*...*/ }

}