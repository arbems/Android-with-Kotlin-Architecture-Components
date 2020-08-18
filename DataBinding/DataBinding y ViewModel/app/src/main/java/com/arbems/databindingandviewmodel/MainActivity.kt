package com.arbems.databindingandviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.arbems.databindingandviewmodel.databinding.UserBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Obtain the ViewModel component (artifact activity-ktx).
         */
        val userModel: UserModel by viewModels()

        /**
         * Inflate view and obtain an instance of the binding class.
         */
        val binding: UserBinding = DataBindingUtil.setContentView(this, R.layout.user)

        /**
         * Assign the component to a property in the binding class.
         */
        binding.viewModel = userModel

        /**
         * Sets the LifecycleOwner that should be used for observing changes of LiveData in this binding.
         */
        binding.lifecycleOwner = this
    }
}