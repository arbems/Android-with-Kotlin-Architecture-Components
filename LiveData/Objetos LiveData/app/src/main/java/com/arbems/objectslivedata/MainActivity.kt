package com.arbems.objectslivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rememberMeCheckBox.isChecked = viewModel.rememberMe.value!!
        rememberMeCheckBox.setOnCheckedChangeListener { _, b ->
            viewModel.setRememberMe(b)
        }

        /**
         * Create the observer which updates the UI, defines the onChanged() method
         * */
        val observer = Observer<Boolean> { newRememberMe ->
            // Update the UI, in this case, a TextView.
            textView.text = newRememberMe.toString()
        }
        
        /**
         * Observe object LiveData with the observe() method
         * Passing in this activity as the LifecycleOwner and the observer.
         * */
        viewModel.rememberMe.observe(this, observer)
    }
}