package com.arbems.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arbems.viewbinding.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        view.name.text = "Alberto Moreno"

        binding.button.setOnClickListener {
            view.name.text = "Carlos Garcia"
        }
    }
}