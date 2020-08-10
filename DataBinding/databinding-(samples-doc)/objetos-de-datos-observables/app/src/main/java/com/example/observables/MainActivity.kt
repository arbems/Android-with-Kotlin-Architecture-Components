package com.example.observables

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.observables.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)

        button.setOnClickListener {
            startActivity(Intent(this, ObservableFieldActivity::class.java))
        }

        button2.setOnClickListener {
            startActivity(Intent(this, ObservableObjectActivity::class.java))
        }
    }
}
