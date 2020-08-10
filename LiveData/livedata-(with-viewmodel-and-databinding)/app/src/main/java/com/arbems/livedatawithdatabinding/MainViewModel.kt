package com.arbems.livedatawithdatabinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {

    /**
     * Create news instances LiveData
     *
     * Usually, MutableLiveData is used in ViewModel and, then, ViewModel only exposes immutable LiveData objects to Observers.
     */
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    private val _age = MutableLiveData<Int>()
    val age: LiveData<Int> get() = _age

    private val _friends = MutableLiveData<List<String>>()
    val friends: LiveData<List<String>> get() = _friends


    fun setName(newName: String) {
        /** MutableLiveData class has public methods setValue(T) y postValue(T). */
        _name.value = newName
    }

    fun setAge(newAge: Int) {
        /** The call to setValue() or postValue() activates Observers and update the UI. */
        _age.value = Random.nextInt(10,80)
    }
}