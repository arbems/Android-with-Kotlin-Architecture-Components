package com.arbems.bindingadapters.data

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.arbems.bindingadapters.BR
import java.time.LocalDate
import java.io.Serializable

class User() : BaseObservable(), Serializable {

    companion object {
        fun build(
            _name: String,
            _firstName: String,
            _lastName: String,
            _age: Int,
            _height: Double,
            _currency: Float,
            _imageUrl: String
        ): User {
            val user = User()
            user.name = _name
            user.firstName = _firstName
            user.lastName = _lastName
            user.age = _age
            user.height = _height
            user.currency = _currency
            user.imageUrl = _imageUrl

            return user
        }
    }

    // La anotación Bindable se debe aplicar a cualquier método de acceso getter de una clase Observable.
    // Bindable generará un campo en la clase BR para identificar el campo que ha cambiado.
    @get:Bindable
    var name: String = ""
        set(value) {
            field = value

            // Notifica a los oyentes que una propiedad específica ha cambiado.
            notifyPropertyChanged(BR.name)
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

    @get:Bindable
    var height: Double = 1.0
        set(value) {
            field = value

            notifyPropertyChanged(BR.height)
        }

    @get:Bindable
    var currency: Float = 1.0F
        set(value) {
            field = value

            notifyPropertyChanged(BR.currency)
        }

    @get:Bindable
    var imageUrl: String = ""
        set(value) {
            field = value

            notifyPropertyChanged(BR.imageUrl)
        }

    // Fields calculates
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
}