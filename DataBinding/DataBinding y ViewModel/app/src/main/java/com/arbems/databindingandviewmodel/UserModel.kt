package com.arbems.databindingandviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserModel : ViewModel() {

    private val _rememberMe = MutableLiveData<Boolean>()
    val rememberMe: LiveData<Boolean> get() = _rememberMe

    fun rememberMeChanged() {
        _rememberMe.value = !_rememberMe.value!!
    }

    init {
        _rememberMe.value = true
    }
}