package com.arbems.databindinginterfaceobservable.data

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import com.arbems.databindinginterfaceobservable.BR
import java.time.LocalDate
import java.io.Serializable

class User : Observable, Serializable {

    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()

    companion object {
        fun build(
            _name: String,
            _firstName: String,
            _lastName: String,
            _age: Int
        ): User {
            val user = User()
            user.name = _name
            user.firstName = _firstName
            user.lastName = _lastName
            user.age = _age

            return user
        }
    }


    /**
     * La anotación Bindable se debe aplicar a cualquier método de acceso getter de una clase Observable.
     * Bindable generará un campo en la clase BR para identificar el campo que ha cambiado.
     * */
    @get:Bindable
    var name: String = ""
        set(value) {
            field = value

            /**
             * Notifica a los oyentes que una propiedad específica ha cambiado.
             * */
            notifyPropertyChanged(BR.name)

            /**
             * Notifica a los oyentes que todas las propiedades de esta instancia han cambiado.
             * */
            // notifyChange()
        }

    @get:Bindable
    var firstName: String = ""
        set(value) {
            field = value

            notifyPropertyChanged(BR.firstName)
        }

    @get:Bindable
    var lastName: String = ""
        set(value) {
            field = value

            notifyPropertyChanged(BR.lastName)
        }

    @get:Bindable
    var age: Int = 0
        set(value) {
            field = value

            notifyPropertyChanged(BR.age)
        }

    // Calculated fields
    @get:Bindable("age")
    var yearOfBirth: Int = 0
        get() {
            LocalDate.now().apply {
                return (year - age)
            }
        }

    @get:Bindable("name", "firstName", "lastName")
    var fullName: String = ""
        get() {
            return "${name.capitalize()} ${firstName.capitalize()} ${lastName.capitalize()}"
        }


    override fun addOnPropertyChangedCallback(
        callback: Observable.OnPropertyChangedCallback) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(
        callback: Observable.OnPropertyChangedCallback) {
        callbacks.remove(callback)
    }

    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    private fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }
}