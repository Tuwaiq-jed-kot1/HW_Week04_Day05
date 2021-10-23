package com.sumaya.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sumaya.myapplication.Fragments.InfoFragment

import com.sumaya.myapplication.Fragments.MainFragment


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainFragment = MainFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
            mainFragment).commit()
    }


    }