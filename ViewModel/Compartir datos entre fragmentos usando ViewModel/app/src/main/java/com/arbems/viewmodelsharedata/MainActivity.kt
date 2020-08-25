package com.arbems.viewmodelsharedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Use the 'by viewModels()' Kotlin property delegate from the activity-ktx artifact
    private val viewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectedObserver = Observer<Boolean> { selected ->
            textResult.text = selected?.toString()
        }
        viewModel.selected.observe(this, selectedObserver)
    }

}