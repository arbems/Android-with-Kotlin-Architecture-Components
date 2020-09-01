package com.arbems.databindingviewmodelobservabletwoway.data

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.arbems.databindingviewmodelobservabletwoway.BR
import java.time.LocalDate
import java.io.Serializable

class User : BaseObservable(), Serializable {

    companion object {
        fun build(
            _name: String,
            _firstName: String,
            _lastName: String,
            _age: Int,
            _rememberMe: Boolean
        ): User {
            val user = User()
            user.name = _name
            user.firstName = _firstName
            user.lastName = _lastName
            user.age = _age
            user.rememberMe = _rememberMe

            return user
        }
    }

    @get:Bindable
    var name: String = ""
        set(value) {
            field = value

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
    var rememberMe: Boolean = false
        set(value) {
            field = value

            notifyPropertyChanged(BR.rememberMe)
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

}