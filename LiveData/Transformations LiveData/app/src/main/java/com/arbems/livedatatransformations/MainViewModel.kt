package com.arbems.livedatatransformations

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _searchLive = MutableLiveData<String>()
    val searchLive: LiveData<String> get() = _searchLive

    val apiLive: LiveData<String> = Transformations.map(searchLive) { query ->
        Log.i(
            "MainViewModel",
            "Transformations.map"
        )
        return@map "api.call($query)"
    }

    fun setSearchLive(query: String) {
        Log.i(
            "MainViewModel",
            "setSearchLive"
        )
        _searchLive.postValue(query)
    }
}