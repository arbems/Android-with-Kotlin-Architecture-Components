package com.example.databindingdoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databindingdoc.data.User
import com.example.databindingdoc.databinding.ActivityEBinding

class EActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityEBinding = DataBindingUtil.setContentView(this, R.layout.activity_e)
        binding.user = User("Moreno", "Simón", 36, null, "Alberto", listOf("Juan", "Roberto", "María"), mapOf("1" to "Fútbol", "2" to "Tenis"))
    }
}