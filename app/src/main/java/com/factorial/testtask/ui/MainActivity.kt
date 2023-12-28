package com.factorial.testtask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.factorial.testtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // no memory leak cuz we have 1 activity
    // and we don't need to clear binding
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}