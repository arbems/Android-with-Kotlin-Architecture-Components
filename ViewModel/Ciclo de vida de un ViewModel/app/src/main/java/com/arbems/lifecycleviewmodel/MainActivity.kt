package com.arbems.lifecycleviewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.arbems.lifecycleviewmodel.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory("ViewModel MainActivity")
        ).get(MyViewModel::class.java)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        buttonSum.setOnClickListener {
            viewModel.sum()
        }

        buttonOpenNew.setOnClickListener {
            val intent = Intent(this, AActivity::class.java)
            startActivity(intent)
        }

        Log.i("Activity Main", "onCreate!")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Activity Main", "onStart!")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Activity Main", "onResume!")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Activity Main", "onPause!")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Activity Main", "onStop!")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Activity Main", "onDestroy!")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Activity Main", "onRestart!")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.i("Activity Main", "onBackPressed!")
    }
}