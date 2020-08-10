package com.arbems.viewmodellivedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    init {
        _name.value = "Click me!"
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun changeName() {
        _name.value = "Ouh Yeaah!"
    }
}