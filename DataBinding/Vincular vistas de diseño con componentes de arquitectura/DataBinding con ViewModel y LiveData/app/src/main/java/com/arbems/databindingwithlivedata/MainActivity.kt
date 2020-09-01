package com.arbems.databindingwithlivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.arbems.databindingwithlivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Obtain the ViewModel component (artifact activity-ktx)
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate view and obtain an instance of the binding class
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Assign the component to a property in the binding class
        binding.viewModel = viewModel

        // Sets the LifecycleOwner that should be used for observing changes of LiveData in this binding
        binding.lifecycleOwner = this
    }
}