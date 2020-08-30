package com.arbems.fieldsandcollectionsobservable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.*
import com.arbems.fieldsandcollectionsobservable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
    }
}