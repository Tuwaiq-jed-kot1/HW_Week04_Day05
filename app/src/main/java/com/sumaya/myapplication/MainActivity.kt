package com.sumaya.myapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.hbb20.CountryCodePicker
import java.util.*

class MainActivity : AppCompatActivity() {
    //0. Name
    private lateinit var name: EditText

    //1. Date
    private lateinit var pickDate : TextView

    //2. Country Code
    private lateinit var ccp: CountryCodePicker
    private var countryCode:String? = null
    private var countryName:String? = null
    private lateinit var showInfo : TextView
    private lateinit var phone: EditText

    //3. Gender Spinner
    private lateinit var spinner1_gender: Spinner
    private val gender: Array<String> = arrayOf("Male", "Female", "I'd rather not say")
    private var genderSelected: String = ""

    private lateinit var send: Button
    private lateinit var clear : Button
    private lateinit var date :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //0. Name Edit Text
        name = findViewById(R.id.editText_Name)

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

        //3. Gender Spinner
        spinner1_gender = findViewById(R.id.spinner1_gender)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, gender)
        spinner1_gender.adapter = arrayAdapter
        spinner1_gender.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@MainActivity,  "You selected ${gender[position]}", Toast.LENGTH_SHORT).show()
                genderSelected = gender[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@MainActivity,  "No selection made", Toast.LENGTH_SHORT).show()
            }

        }

        //show Info
        send = findViewById(R.id.send)
        send.setOnClickListener {
            var info = "Sent information is as follows:\n"
            info += "Name: ${name.text}\n"
            info += "Birthday: $date\n"
            info += "Phone: +${countryCode.toString()+ phone.text}\n"
            info += "Gender: $genderSelected"
            showInfo.setText(info)
        }

        //4. Alert dialog
        clear= findViewById(R.id.clear)
        clear.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Reset")
            alert.setIcon(R.drawable.alert)
            alert.setMessage("Are you sure you want to clear all entries?")
            alert.setPositiveButton(R.string.yes) { dialog, which ->
                name.setText(null)
                pickDate.setText(null)
                phone.setText(null)
                showInfo.setText(null)
                ccp.resetToDefaultCountry()
                //arrayAdapter.set
                //spinner1_gender.resetPivot()
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
}