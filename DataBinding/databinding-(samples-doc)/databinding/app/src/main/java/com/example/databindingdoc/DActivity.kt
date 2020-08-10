package com.example.databindingdoc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databindingdoc.data.User
import com.example.databindingdoc.databinding.ActivityDBinding


class DActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDBinding = DataBindingUtil.setContentView(this, R.layout.activity_d)
        binding.user = User("Moreno", "Simón", 36, null, "Alberto", listOf("Juan", "Roberto", "María"), mapOf("1" to "Fútbol", "2" to "Tenis"))
        binding.large = true
        binding.orangeCount = 1
    }
}
