package com.arbems.fieldsandcollectionsobservable

import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var user : User = User(
        ObservableField<String>(""),
        ObservableField<String>(""),
        ObservableField<String>(""),
        ObservableInt(0),
        ObservableArrayMap<String, Any>().apply {
            set("es", "Spanish")
        }
    )

    fun setUser(name: String, firstName: String, lastName: String, age: String) {
        user.name.set(name)
        user.firstName.set(firstName)
        user.lastName.set(lastName)

        if(age != "")
            user.age.set(age.toInt())
    }
}