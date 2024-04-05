package com.example.kotlintut.activity

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlintut.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getStartedButton.setOnTouchListener { _, event ->
            when(event.action){
                MotionEvent.ACTION_DOWN -> {
                    // Handle touch down event
                    true
                }
                MotionEvent.ACTION_UP -> {
                    // Handle touch up event
                    binding.getStartedButton.performClick() // button is clicked
                    true
                }
                else -> false // Return false for other touch events
            }
        }

        binding.getStartedButton.setOnClickListener {
            // Define the action you want to perform when the button is clicked
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
        }

    }
}