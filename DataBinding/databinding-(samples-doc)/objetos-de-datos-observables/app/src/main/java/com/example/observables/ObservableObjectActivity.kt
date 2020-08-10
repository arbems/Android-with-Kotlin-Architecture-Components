package com.example.observables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.observables.data.User
import com.example.observables.databinding.ActivityObservableObjectBinding

class ObservableObjectActivity : AppCompatActivity() {

    private val user: User = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityObservableObjectBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_observable_object)

        binding.user = user
    }

    fun changeNames(view: View) {
        user.firstName = "Jesus"
        user.lastName = "Garcia"
    }
}
