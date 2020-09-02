package com.arbems.databindingwithlivedata.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import java.time.LocalDate
import java.util.*

class User {

    companion object {
        fun build(name: String, firstName: String, lastName: String, age: Int): User {
            val user = User()
            user._name.value = name
            user._firstName.value = firstName
            user._lastName.value = lastName
            user._age.value = age

            return user
        }
    }

    private val _name = MutableLiveData<String>("")
    val name: LiveData<String> = Transformations.map(_name) { name ->
        name.capitalize(Locale.ROOT)
    }

    private val _firstName = MutableLiveData<String>("")
    val firstName: LiveData<String> = Transformations.map(_firstName) { firstName ->
        firstName.capitalize(Locale.ROOT)
    }

    private val _lastName = MutableLiveData<String>("")
    val lastName: LiveData<String> = Transformations.map(_lastName) { lastName ->
        lastName.capitalize(Locale.ROOT)
    }

    private val _age = MutableLiveData<Int>(0)
    val age: LiveData<Int> get() = _age

    // Usually, MutableLiveData is used in ViewModel and, then, ViewModel only exposes immutable LiveData objects to Observers.
    private val _rememberMe = MutableLiveData<Boolean>(false)
    val rememberMe: LiveData<Boolean> get() = _rememberMe

    // Methods
    fun rememberMeChanged() {
        // The call to setValue() activates Observers and update the UI.
        _rememberMe.value = rememberMe.value == false
    }

    // Calculated fields
    val yearOfBirth: LiveData<Int> = Transformations.map(_age) { age ->
        val year = LocalDate.now().year
        year - age
    }

    fun setName(value: String) {
        _name.value = value
    }

    fun setFirstName(value: String) {
        _firstName.value = value
    }

    fun setLastName(value: String) {
        _lastName.value = value
    }

    fun setAge(value: Int) {
        _age.value = value
    }
}