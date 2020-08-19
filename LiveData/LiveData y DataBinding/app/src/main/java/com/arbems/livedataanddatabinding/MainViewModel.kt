package com.arbems.livedataanddatabinding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel : ViewModel() {
    /**
     * Create objects LiveData
     * Usually, MutableLiveData is used in ViewModel and, then, ViewModel only exposes immutable LiveData objects to Observers.
     *
     * Overall, LiveData only provides updates when data changes, and only active observers.
     * Also, if the Observer changes from inactive to active a second time, they only receive an update if the value changed since the last time they were active.
     */
    private val _rememberMe = MutableLiveData<Boolean>()
    val rememberMe: LiveData<Boolean> get() = _rememberMe

    fun rememberMeChanged() {
        // The call to setValue() activates Observers and update the UI.
        _rememberMe.value = rememberMe.value == false
    }

    init {
        _rememberMe.value = false
    }
}