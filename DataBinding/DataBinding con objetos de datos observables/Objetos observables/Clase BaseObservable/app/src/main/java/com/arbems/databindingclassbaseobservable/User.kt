package com.arbems.databindingclassbaseobservable

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import java.time.LocalDate

class User() : BaseObservable() {

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

            fullName = value

            /**
             * Notifica a los oyentes que una propiedad específica ha cambiado.
             * */
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

    // Fields calculates
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
}