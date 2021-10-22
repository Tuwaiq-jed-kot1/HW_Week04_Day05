package com.sumaya.myapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.hbb20.CountryCodePicker
import com.sumaya.myapplication.Fragment.MainFragment
import java.util.*

class MainActivity : AppCompatActivity() {
    //1. Date
    private lateinit var pickDate : TextView

    //2. Country Code
    private lateinit var ccp: CountryCodePicker
    private var countryCode:String? = null
    private var countryName:String? = null
    private lateinit var showInfo : TextView
    private lateinit var phone: EditText

    //3. gender
    private lateinit var gender :TextView

    private lateinit var send: Button
    private lateinit var clear : Button
    private lateinit var date :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val mainFragment = MainFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,MainFragment.newInstance()).commitNow()

    /*    if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()*/
     /*
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
                gender.setText(null)
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

        //4.Alert gender dialog
        gender=findViewById(R.id.id_gender)
        gender.setOnClickListener {
            val alert_gender =AlertDialog.Builder(this)
            alert_gender.setTitle("Gender")

            alert_gender.setMessage("Please choose your gender")

            alert_gender.setPositiveButton("Male"){dialog , which ->
                gender.setText("Male")
            }

            alert_gender.setNegativeButton("Fmale"){dialog , which ->
                gender.setText("Fmale")
            }

            alert_gender.setNeutralButton(R.string.cancel) { dialog, which ->
                dialog.cancel()
            }.show()

        }

*/
    }
}