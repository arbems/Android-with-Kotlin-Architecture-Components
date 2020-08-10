package com.arbems.databindingviewmodelandlivedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    private val _age = MutableLiveData<Int>()
    val age: LiveData<Int> get() = _age


    init {
        _name.value = "Alberto"
        _age.value = 37
    }

    override fun onCleared() {
        super.onCleared()
    }
}