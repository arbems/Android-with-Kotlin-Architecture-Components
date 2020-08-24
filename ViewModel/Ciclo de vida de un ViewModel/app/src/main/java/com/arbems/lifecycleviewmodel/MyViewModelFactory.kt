package com.arbems.lifecycleviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Implementations of the Factory interface are responsible for creating instances of ViewModels.
 */

class MyViewModelFactory (private val name: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(name) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}