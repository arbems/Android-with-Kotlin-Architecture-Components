package com.arbems.databindingviewmodelandlivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.arbems.databindingviewmodelandlivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Get a instance of viewModel
         */
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        /**
         * Inflate view and obtain an instance of the binding class
         * Update viewModel to databinding
         */
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        /**
         * Set the owner of lifecycle usar para observar los cambios de LiveData en este enlace.
         * If one LiveData is in a link expressions and we don't set LifecycleOwner, LiveData will not observe them
         * and the updates not show in the IU.
         */
        binding.lifecycleOwner = this
    }
}