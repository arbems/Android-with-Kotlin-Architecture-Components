package com.arbems.lifecycleviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_a.*

class AActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)

        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory("ViewModel Activity A")
        ).get(MyViewModel::class.java)

        val fragment = MyFragment()
        buttonAddFragment.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }

        buttonDeleteFragment.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .remove(fragment)
                .commitNow()
        }
        
        Log.i("Activity A", "onStart!")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Activity A", "onStart!")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Activity A", "onResume!")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Activity A", "onPause!")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Activity A", "onStop!")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Activity A", "onDestroy!")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Activity A", "onRestart!")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.i("Activity A", "onBackPressed!")
    }
}