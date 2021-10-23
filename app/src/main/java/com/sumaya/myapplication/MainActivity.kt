package com.sumaya.myapplication

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.sumaya.myapplication.frag.FragOne
import com.sumaya.myapplication.frag.FragTwo

class MainActivity : AppCompatActivity() {
    class MainActivity : AppCompatActivity() {
        public override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val firstFragment = FragOne()
            supportFragmentManager.beginTransaction().replace(R.id.frame, firstFragment)
                .commit()

            val bundle = Bundle()
            val transaction = this.supportFragmentManager.beginTransaction()
            val fragmentTwo = FragTwo()
            fragmentTwo.arguments = bundle
            transaction.replace(R.id.frame, fragmentTwo)
            transaction.addToBackStack(null)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()
        }
    }}


