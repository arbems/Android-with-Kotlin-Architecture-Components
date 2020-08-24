package com.arbems.lifecycleviewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel(private val name: String): ViewModel() {
    private val _textMsg = MutableLiveData<Int>()
    val textMsg: LiveData<Int> get() = _textMsg

    init {
        _textMsg.value = 0
        Log.i("$name", "created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("$name", "destroyed!")
    }

    fun sum() {
        _textMsg.value = _textMsg.value?.plus(1)
    }
}