package com.arbems.databindingbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.arbems.databindingbasic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         *  Binding class generated, for each design file a binding class is generated.
         *
         *  To access design variables and design views.
         *  Knows how to assign values for binding expressions.
         */
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)

        binding.user = User("username", "password")
    }
}