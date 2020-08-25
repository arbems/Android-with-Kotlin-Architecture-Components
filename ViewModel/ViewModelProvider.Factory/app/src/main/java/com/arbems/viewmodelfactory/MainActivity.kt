package com.arbems.viewmodelfactory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.arbems.viewmodelfactory.utils.BaseViewModelFactory
import com.arbems.viewmodelfactory.utils.getViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         *  1. Using ViewModelFactory:
         *
         *  ViewModelFactory create instances of ViewModel objects, with or without constructor parameters.
         *  Note: ViewModelProvider without factory only creates instances without constructor parameters
         */
        val viewModelFactory = MainViewModelFactory("Alberto", 30)
        val viewModel01 = ViewModelProvider(this, viewModelFactory)
                .get(MainViewModel::class.java)


        /**
         *  2. Using BaseViewModelFactory:
         */
        val viewModel02: MainViewModel = ViewModelProvider(this,
            BaseViewModelFactory {
                MainViewModel(
                    "Alberto",
                    30
                )
            }).get(MainViewModel::class.java)


        /**
         *  3. Using ViewModelUtils:
         */
        val viewModel03: MainViewModel = getViewModel { MainViewModel("Alberto", 30) }
        // or
        val viewModel04 = getViewModel<MainViewModel>()
        // or
        val viewModel05: MainViewModel = getViewModel()
        // or
        val activityScopedViewModel = this?.getViewModel<MainViewModel>()

    }
}