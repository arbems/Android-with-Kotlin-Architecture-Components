package com.arbems.databindingtwoway

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserModel : ViewModel() {

    val rememberMe = MutableLiveData<Boolean>()
    // val rememberMe: LiveData<Boolean> get() = _rememberMe
    val name = MutableLiveData<String>()

    fun setRememberMe() {
        rememberMe.value = !rememberMe.value!!
    }

    fun setName(name: String) {
        // this.name.value = name
    }

    init {
        rememberMe.value = true
        name.value = ""
    }
}