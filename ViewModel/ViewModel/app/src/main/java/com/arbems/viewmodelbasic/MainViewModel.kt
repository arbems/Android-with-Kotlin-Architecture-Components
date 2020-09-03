package com.arbems.viewmodelbasic

import androidx.lifecycle.ViewModel
import android.util.Log

/**
 * Crea un ViewModel
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

    /**
     * Methods for updating the UI
     */
    fun onSkip() { /*...*/ }
    fun onCorrect() { /*...*/ }

}