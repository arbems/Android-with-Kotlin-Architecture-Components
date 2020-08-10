package com.example.databindingdoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databindingdoc.data.User
import com.example.databindingdoc.databinding.ActivityABinding

class AActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityABinding = DataBindingUtil.setContentView(this, R.layout.activity_a)
        binding.user = User("Moreno", "Simón", 37, null)
    }
}
