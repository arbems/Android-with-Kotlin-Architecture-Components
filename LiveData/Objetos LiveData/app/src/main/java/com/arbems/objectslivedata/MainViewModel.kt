package com.arbems.objectslivedata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel : ViewModel() {
    /**
     * Create object LiveData
     * */
    private val _rememberMe = MutableLiveData<Boolean>()
    val rememberMe: LiveData<Boolean> get() = _rememberMe

    fun setRememberMe(rememberMe: Boolean) {
        _rememberMe.value = rememberMe
    }

    init {
        _rememberMe.value = false
    }
}