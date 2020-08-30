package com.arbems.databindingviewmodelobservable

import androidx.lifecycle.ViewModel
import androidx.databinding.*
import java.time.LocalDate

class MainViewModel : ViewModel(), Observable {
    
    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()

    @get:Bindable
    var name: String = ""
        set(value) {
            field = value

            fullName = value

            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var firstName: String = ""
        set(value) {
            field = value

            fullName = value

            notifyPropertyChanged(BR.firstName)
        }

    @get:Bindable
    var lastName: String = ""
        set(value) {
            field = value

            fullName = value

            notifyPropertyChanged(BR.lastName)
        }

    @get:Bindable
    var age: Int = 0
        set(value) {
            field = value

            yearOfBirth = value

            notifyPropertyChanged(BR.age)
        }

    // Fields calculate
    @get:Bindable
    var yearOfBirth: Int = 0
        set(value) {
            LocalDate.now().apply {
                field = (year - value)
            }

            notifyPropertyChanged(BR.yearOfBirth)
        }

    @get:Bindable
    var fullName: String = ""
        set(value) {
            field = "${name.capitalize()} ${firstName.capitalize()} ${lastName.capitalize()}"

            notifyPropertyChanged(BR.fullName)
        }

    fun setUser(_name: String, _firstName: String, _lastName: String, _age: String) {
        name = _name
        firstName = _firstName
        lastName = _lastName

        if(_age != "")
            age = _age.toInt()
    }

    override fun addOnPropertyChangedCallback(
        callback: Observable.OnPropertyChangedCallback) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(
        callback: Observable.OnPropertyChangedCallback) {
        callbacks.remove(callback)
    }

    private fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    private fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }
}