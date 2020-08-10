package com.example.databindingdoc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val intent: Intent = Intent(this, AActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent: Intent = Intent(this, BActivity::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener {
            val intent: Intent = Intent(this, CActivity::class.java)
            startActivity(intent)
        }

        button4.setOnClickListener {
            val intent: Intent = Intent(this, DActivity::class.java)
            startActivity(intent)
        }

        button5.setOnClickListener {
            val intent: Intent = Intent(this, EActivity::class.java)
            startActivity(intent)
        }
    }
}
