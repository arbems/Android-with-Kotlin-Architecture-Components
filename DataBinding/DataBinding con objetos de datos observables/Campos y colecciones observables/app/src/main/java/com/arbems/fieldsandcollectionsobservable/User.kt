package com.arbems.fieldsandcollectionsobservable

import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import java.time.LocalDate

class User(
    val name: ObservableField<String>,
    val firstName: ObservableField<String>,
    val lastName: ObservableField<String>,
    val age: ObservableInt,
    val languages: ObservableArrayMap<String, Any>
) {
    // Fields calculates
    val yearOfBirth: ObservableInt = object : ObservableInt(age) {
        override fun get(): Int {
            LocalDate.now().apply {
                return year - age.get()
            }
        }
    }

    val fullName: ObservableField<String> =
        object : ObservableField<String>(name, firstName, lastName) {
            override fun get(): String? {
                return "${name.get()?.capitalize()} ${firstName.get()?.capitalize()} ${lastName.get()?.capitalize()}"
            }
        }
}