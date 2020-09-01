package com.arbems.databindingwithlivedata

import androidx.lifecycle.ViewModel
import com.arbems.databindingwithlivedata.data.User

class MainViewModel : ViewModel() {

    var user = User.build("", "", "", 0)

    fun setUser(name: String, firstName: String, lastName: String, age: String) {
        user.setName(name)
        user.setFirstName(firstName)
        user.setLastName(lastName)

        if (age != "")
            user.setAge(age.toInt())
    }

    fun resetUser() {
        user.setName("")
        user.setFirstName("")
        user.setLastName("")
        user.setAge(0)
    }
}