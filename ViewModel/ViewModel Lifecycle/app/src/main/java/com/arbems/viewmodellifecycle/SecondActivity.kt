package com.arbems.viewmodellifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private val lifecycleObserver = LifecycleObserver("MyActivity", lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        lifecycle.addObserver(lifecycleObserver)

        button.setOnClickListener {
            this.finish()
        }
    }
}