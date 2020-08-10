package com.arbems.livedatabasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        /**
         * Create a Observer object which update the UI
         *
         * Overall, LiveData only provides updates when data changes, and only active observers.
         * Also, if the Observer changes from inactive to active a second time, they only receive an update if the value changed since the last time they were active.
         */
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            name.text = newName
        }
        val ageObserver = Observer<Int> { newAge ->
            age.text = newAge?.toString()
        }

        // Observe of LiveData, sending the activity as LifecycleOwner and Observer.
        viewModel.name.observe(this, nameObserver)
        viewModel.age.observe(this, ageObserver)
    }

    fun update(view : View) {
        viewModel.setName("Jesus")
        viewModel.setAge(32)
    }
}