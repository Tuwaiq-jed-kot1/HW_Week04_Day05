package com.sumaya.myapplication
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainfragment = MainFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.FragemtFrame, mainfragment)
            commit()
        }

    }
}