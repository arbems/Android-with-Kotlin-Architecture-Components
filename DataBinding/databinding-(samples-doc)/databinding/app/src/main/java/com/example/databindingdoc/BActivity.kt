package com.example.databindingdoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databindingdoc.data.User
import com.example.databindingdoc.databinding.ActivityBBinding

class BActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityBBinding = DataBindingUtil.setContentView(this, R.layout.activity_b)
        binding.user = User("Moreno", "Simón", 36, null, "Alberto", listOf("Juan", "Roberto", "María"))
    }
}
