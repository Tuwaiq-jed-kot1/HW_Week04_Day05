package com.sumaya.myapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.hbb20.CountryCodePicker
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var Gender = arrayOf("Male", "Female")
    private lateinit var spinner: Spinner
    private lateinit var textView_massage:TextView

    //1. Date
    private lateinit var pickDate : TextView

    //2. Country Code
    private lateinit var ccp: CountryCodePicker
    private var countryCode:String? = null
    private var countryName:String? = null
    private lateinit var showInfo : TextView
    private lateinit var phone: EditText

    private lateinit var send: Button
    private lateinit var clear : Button
    private lateinit var date :String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView_massage = findViewById(R.id.massage)


        spinner = findViewById(R.id.spinner_sample)
        spinner.onItemSelectedListener = this


        // Create an ArrayAdapter using a simple spinner layout and languages array
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Gender)
        // Set layout to use when the list of choices appear
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner.adapter = arrayAdapter


        // 1. Date Dialog
        pickDate= findViewById(R.id.pickDate)

        //create object of Calendar
        val calendar = Calendar.getInstance()
        // add day of month
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        pickDate.setOnClickListener {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ _, y,m,d ->
                date = "$d/$m/$y"
                pickDate.setText(date)
            },year,month, day)
                .show()
        }

        //2. Country Code
        showInfo = findViewById(R.id.info)
        phone = findViewById(R.id.phone)
        ccp = findViewById(R.id.pickCode)
        ccp.setOnCountryChangeListener {
            countryCode = ccp.selectedCountryCode
            countryName = ccp.selectedCountryName
        }

        //show Info
        send = findViewById(R.id.send)
        send.setOnClickListener {
            var info = "Birthday: $date\n"
            info += "Phone: +${countryCode.toString()+ phone.text}\n"
            showInfo.setText(info)
        }

        //3. Alert dialog
        clear= findViewById(R.id.clear)
        clear.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Reset")
            alert.setIcon(R.drawable.alert)
            alert.setMessage("Are you sure you want to clear all entries?")
            alert.setPositiveButton(R.string.yes) { dialog, which ->
                pickDate.setText(null)
                phone.setText(null)
                showInfo.setText(null)
                ccp.resetToDefaultCountry()
            }
            alert.setNegativeButton(R.string.no) { dialog, which ->
                dialog.cancel()
            }
            alert.setNeutralButton(R.string.cancel) { dialog, which ->
                dialog.cancel()
            }
            alert.show()
        }


    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        textView_massage.text = "Gender : "+Gender[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}