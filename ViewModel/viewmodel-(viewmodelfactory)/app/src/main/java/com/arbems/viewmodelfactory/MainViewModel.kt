package com.arbems.viewmodelfactory

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel(name: String, age: Int): ViewModel() {
    var name = name
    var age = age

    init {
        Log.i("Info", "Name is $name and age is $age")
    }
}