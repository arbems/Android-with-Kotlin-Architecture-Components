package com.arbems.databindingbidirectionalfieldsobservable

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val editTextContent = ObservableField<String>()
}