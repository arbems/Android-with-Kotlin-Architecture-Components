package com.arbems.databindingviewmodelobserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.arbems.databindingviewmodelobserver.databinding.HeroBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val binding: HeroBinding = DataBindingUtil.setContentView(this, R.layout.hero)
        binding.viewModel = viewModel
    }
}