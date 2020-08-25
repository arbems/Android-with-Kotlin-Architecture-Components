package com.arbems.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Implementations of the Factory interface are responsible for creating instances of ViewModels.
 */

class MainViewModelFactory (private val name: String, private val age: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(name, age) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}