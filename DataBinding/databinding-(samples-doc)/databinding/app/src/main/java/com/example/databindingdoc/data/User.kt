package com.example.databindingdoc.data

import kotlin.random.Random

data class User(val firstName: String, val lastName: String, val age: Int, val id: Int? = Random.nextInt(999), val displayName: String? = null, val list: List<String>? = null, val map: Map<String, String>? = null)