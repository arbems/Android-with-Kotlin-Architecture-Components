package com.arbems.databindingviewmodelwithlivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.arbems.databindingviewmodelwithlivedata.databinding.UserBinding

class MainActivity : AppCompatActivity() {

    // Obtain the ViewModel component (artifact activity-ktx)
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate view and obtain an instance of the binding class
        val binding: UserBinding = DataBindingUtil.setContentView(this, R.layout.user)

        // Assign the component to a property in the binding class
        binding.viewModel = viewModel

        // Sets the LifecycleOwner that should be used for observing changes of LiveData in this binding
        binding.lifecycleOwner = this
    }
}