package com.sumaya.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    val mainFragment = MainFragment()
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.frameFragemt, mainFragment)
        commit()
    }
}}

