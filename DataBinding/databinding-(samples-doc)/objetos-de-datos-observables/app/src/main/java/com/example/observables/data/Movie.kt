package com.example.observables.data

import androidx.databinding.*

data class Movie (
    val title: ObservableField<String>,
    val country: ObservableField<String>,
    val languages: ObservableArrayMap<String, Any>,
    val actors: ObservableArrayList<Any>,
    val year: ObservableInt,
    val blackAndWithe: ObservableBoolean,
    val likes: ObservableInt
)