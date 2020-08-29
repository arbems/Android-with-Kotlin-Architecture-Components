package com.arbems.databindingbidirectionalobjetsobservable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.arbems.databindingbidirectionalobjetsobservable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val model: MyViewModel by viewModels()

    private val data: String = "data"
    private lateinit var obj: Object

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        obj = savedInstanceState?.getSerializable(data) as Object? ?: Object()

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            viewModel = model
            lifecycleOwner = this@MainActivity
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(data, obj)
    }
}