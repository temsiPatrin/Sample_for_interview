package com.temsipatrin.sampleforinterview.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.temsipatrin.sampleforinterview.R
import com.temsipatrin.sampleforinterview.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainActivityBinding.inflate(layoutInflater)
//        setUpNavigation()
        setContentView(binding.root)

    }
    private fun setUpNavigation(){
        val navController = Navigation.findNavController(this, R.id.myNavHostFragment)
    }
}