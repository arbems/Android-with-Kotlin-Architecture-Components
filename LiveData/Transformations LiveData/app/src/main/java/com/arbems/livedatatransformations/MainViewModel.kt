package com.arbems.livedatatransformations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.arbems.livedatatransformations.data.User


class MainViewModel : ViewModel() {

    // Transformations.switchMap
    val userId: MutableLiveData<String> = MutableLiveData<String>()
    val user = Transformations.switchMap(userId) { id ->
        getUser(id)
    }

    // Transformations.map
    val userName: LiveData<String> = Transformations.map(user) { user ->
        "${user.name} ${user.lastName}"
    }

    private fun getUser(id: String): LiveData<User> {

        if (id == null || id == "")
            return MutableLiveData<User>()

        val users = arrayListOf<User>(
            User("1", "Alberto", "Moreno"),
            User("2", "Carlos", "Garcia"),
            User("3", "Jesus", "Silvestre"),
            User("4", "Antonio", "Limón"),
            User("5", "Fernando", "Lechero"),
            User("6", "Jose", "Grillo"),
            User("7", "Agustin", "Escalador"),
            User("8", "Roberto", "Gutierrez"),
            User("9", "Ignacio", "Perez"),
        )

        var user = users.filter {
            it.id == id
        }

        return if (user.isNotEmpty())
            MutableLiveData<User>(user.first())
        else
            MutableLiveData<User>()
    }
}