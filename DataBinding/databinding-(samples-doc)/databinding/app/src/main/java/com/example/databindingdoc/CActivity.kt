package com.example.databindingdoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databindingdoc.data.User
import com.example.databindingdoc.databinding.ActivityCBinding

class CActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityCBinding = DataBindingUtil.setContentView(this, R.layout.activity_c)
        binding.user = User("Moreno", "Simón", 36, null, "Alberto", listOf("Juan", "Roberto", "María"), mapOf("1" to "Fútbol", "2" to "Tenis"))
        binding.index = 1
    }
}
