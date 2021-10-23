package com.sumaya.myapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.hbb20.CountryCodePicker
import java.util.*
import android.widget.Toast

import android.widget.RadioGroup





class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction().replace(R.id.container,MainFragment.newInstance()).commitNow()





    }

}