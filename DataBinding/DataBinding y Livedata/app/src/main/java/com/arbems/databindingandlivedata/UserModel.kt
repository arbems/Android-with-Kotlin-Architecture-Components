package com.arbems.databindingandlivedata

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class UserModel : ViewModel() {

    val rememberMe = ObservableField<Boolean>()

    fun rememberMeChanged() {
        if (rememberMe.get() == false) rememberMe.set(true)
        else  rememberMe.set(false)
    }

    init {
        rememberMe.set(true)
    }
}