package com.arbems.bindingadapters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arbems.bindingadapters.data.User

class MainViewModel: ViewModel() {

    var user: User = User.build(
        "alberto",
        "moreno",
        "simón",
        37,
        1.0
    )
}