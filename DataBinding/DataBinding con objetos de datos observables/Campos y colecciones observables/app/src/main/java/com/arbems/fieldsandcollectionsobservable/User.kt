package com.arbems.fieldsandcollectionsobservable

import androidx.databinding.*

class User (
    val firstName: ObservableField<String>,
    val age: ObservableInt,
    val languages: ObservableArrayMap<String, Any>,
    val actors: ObservableArrayList<Any>
)