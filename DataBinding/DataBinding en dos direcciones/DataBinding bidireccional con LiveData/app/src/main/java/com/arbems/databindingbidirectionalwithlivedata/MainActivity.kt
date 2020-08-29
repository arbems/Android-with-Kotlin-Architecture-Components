package com.arbems.databindingbidirectionalwithlivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.arbems.databindingbidirectionalwithlivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val model: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            viewModel = model
            lifecycleOwner = this@MainActivity
        }
    }
}