package com.arbems.viewmodelsharedatabetweenfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var model: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProvider(this)[SharedViewModel::class.java]

        val selectedObserver = Observer<Boolean> { selected ->
            textResult.text = selected?.toString()
        }
        model.selected.observe(this, selectedObserver)
    }

}