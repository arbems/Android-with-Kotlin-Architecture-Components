package com.arbems.viewmodelbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    /**
     * Declaring ViewModel
     */
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Get a instance of exist viewModel or create a new one.
         *
         * Returns an existing ViewModel or creates a new one in the scope (usually, a fragment or an activity), associated with this ViewModelProvider.
         * And will remain as long as the scope is active (for example, if it's an activity, until it ends, or the process is finished).
         */
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // or
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }
}