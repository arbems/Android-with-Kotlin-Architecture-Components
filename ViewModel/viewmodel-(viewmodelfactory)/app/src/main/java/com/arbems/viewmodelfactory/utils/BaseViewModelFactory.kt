package com.arbems.viewmodelfactory.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *  We can avoid the creation of one factory per different ViewModel by creating a BaseViewModelFactory that receives the creation logic from the outside,
 *  through a lambda, and just calls it when necessary, this already reduces a lot of the boilerplate code
 */

class BaseViewModelFactory<T>(val creator: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator() as T
    }
}