package com.arbems.databindingbidirectionalwithlivedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val editTextContent = MutableLiveData<String>()
}