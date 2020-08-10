package com.arbems.livedatabasic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {

    /**
     * Create news instances LiveData
     */
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    private val _age = MutableLiveData<Int>()
    val age: LiveData<Int> get() = _age

    private val _friends = MutableLiveData<List<String>>()
    val friends: LiveData<List<String>> get() = _friends


    fun setName(newName: String) {
        _name.value = newName
    }

    fun setAge(newAge: Int) {
        _age.value = Random.nextInt(10,80)
    }
}