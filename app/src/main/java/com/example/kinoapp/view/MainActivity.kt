package com.example.kinoapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.kinoapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.frag))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navcontroller = findNavController(R.id.frag)
        return navcontroller.navigateUp() || super.onSupportNavigateUp()
    }
}