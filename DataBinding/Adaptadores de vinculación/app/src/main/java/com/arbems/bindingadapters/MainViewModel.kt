package com.arbems.bindingadapters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _paddingLeft = MutableLiveData<Int>()
    val paddingLeft: LiveData<Int> get() = _paddingLeft

    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> get() = _imageUrl

    init {
        _imageUrl.value = "https://i.imgur.com/tGbaZCY.jpg"
        _paddingLeft.value = 50
    }



}