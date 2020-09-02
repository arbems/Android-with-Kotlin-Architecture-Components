package com.arbems.databindinginterfaceobservable

import androidx.lifecycle.ViewModel
import com.arbems.databindinginterfaceobservable.data.User

class MainViewModel : ViewModel() {

    var user: User = User.build(
        "",
        "",
        "",
        0
    )

    fun setUser(name: String, firstName: String, lastName: String, age: String) {
        user.name = name
        user.firstName = firstName
        user.lastName = lastName

        if(age != "")
            user.age = age.toInt()
    }
}