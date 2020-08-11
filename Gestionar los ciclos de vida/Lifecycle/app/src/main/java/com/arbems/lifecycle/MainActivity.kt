package com.arbems.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var myLocationListener: MyLocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myLocationListener = MyLocationListener(this, lifecycle, "MyLocationListener") { location ->
            // update UI
        }
        lifecycle.addObserver(myLocationListener)

    }
}