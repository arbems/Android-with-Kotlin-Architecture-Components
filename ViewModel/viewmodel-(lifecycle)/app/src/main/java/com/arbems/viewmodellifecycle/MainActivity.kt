package com.arbems.viewmodellifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val lifecycleObserver = LifecycleObserver("MainActivity", lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(lifecycleObserver)

        Log.i("MainActivity", "onCreate!")
        Log.i("MainActivity", "${lifecycle.currentState}!")

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment())
            .commitNow()

        button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        buttonFinish.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart!")
        Log.i("MainActivity", "${lifecycle.currentState}!")
    }

    override fun onResume() {
        super.onResume()

        Log.i("MainActivity", "onResume!")
        Log.i("MainActivity", "${lifecycle.currentState}!")
    }

    override fun onPause() {
        super.onPause()

        Log.i("MainActivity", "onPause!")
        Log.i("MainActivity", "${lifecycle.currentState}!")
    }

    override fun onStop() {
        super.onStop()

        Log.i("MainActivity", "onStop!")
        Log.i("MainActivity", "${lifecycle.currentState}!")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i("MainActivity", "onDestroy!")
        Log.i("MainActivity", "${lifecycle.currentState}!")
    }

    override fun onRestart() {
        super.onRestart()

        Log.i("MainActivity", "onRestart!")
        Log.i("MainActivity", "${lifecycle.currentState}!")
    }

    override fun onBackPressed() {
        super.onBackPressed()

        Log.i("MainActivity", "onBackPressed!")
        Log.i("MainActivity", "${lifecycle.currentState}!")
    }
}
