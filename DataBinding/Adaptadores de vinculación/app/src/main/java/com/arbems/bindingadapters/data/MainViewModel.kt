package com.arbems.bindingadapters.data

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
        1.0,
        1.0F,
        "https://avatars0.githubusercontent.com/u/19887553?s=460&u=1d95208c3ca4091cb9671dcbf9f7ffa580b644e2&v=4"
    )
}