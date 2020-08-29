package com.arbems.objectsobservable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.arbems.objectsobservable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val user: UserObservable = UserObservable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        user.firstName = "Alberto"
        user.lastName = "Moreno"

        binding.user = user
    }

    fun changeUser(view: View) {
        user.firstName = "Carlos"
        user.lastName = "Garcia"
    }
}