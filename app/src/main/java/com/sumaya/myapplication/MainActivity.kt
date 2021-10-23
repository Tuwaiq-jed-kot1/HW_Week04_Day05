package com.sumaya.myapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hbb20.CountryCodePicker
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var pickDate: TextView
    private lateinit var ccp: CountryCodePicker
    private var countryCode: String? = null
    private var countryName: String? = null
    private lateinit var showInfo: TextView
    private lateinit var phone: EditText
    private lateinit var nameEdit: EditText

    private lateinit var send: Button
    private lateinit var clear: Button
    private var date: String = ""
    private lateinit var pickGenderText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, showFragment.newInstance())
                .commitNow()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Date Dialog
        pickDate = view.findViewById(R.id.pickDate)

        //create object of Calendar
        val calendar = Calendar.getInstance()
        // add day of month
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        pickDate.setOnClickListener {
            DatePickerDialog(view.context, { _, y, m, d ->
                date = "$d/$m/$y"
                pickDate.text = date
            }, year, month, day)
                .show()
        }

        //2. Country Code
        showInfo = view.findViewById(R.id.info)
        phone = view.findViewById(R.id.phone)
        ccp = view.findViewById(R.id.pickCode)
        ccp.setOnCountryChangeListener {
            countryCode = ccp.selectedCountryCode
            countryName = ccp.selectedCountryName
        }

        //show Info
        send = view.findViewById(R.id.send)
        send.setOnClickListener {
            var info = "Birthday: $date\n"
            info += "Phone: +${countryCode.toString() + phone.text}\n"
            showInfo.text = info

            val bundleInfo = Bundle()
            bundleInfo.putString("date", date)
            bundleInfo.putString("gender", pickGenderText.text.toString())
            bundleInfo.putString("phone", countryCode.toString() + phone.text)

            val fragment = showInfo.newInstance()
            fragment.arguments = bundleInfo
            val activity = view.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack("details")
                .commit()
        }

        //3. Alert dialog
        clear = view.findViewById(R.id.clear)
        clear.setOnClickListener {
            val alert = AlertDialog.Builder(view.context)
            alert.setTitle("Reset")
            alert.setIcon(R.drawable.alert)
            alert.setMessage("Are you sure you want to clear all entries?")
            alert.setPositiveButton(R.string.yes) { _, _ ->
                pickDate.text = null
                phone.text = null
                showInfo.text = null
                pickGenderText.text = null
                ccp.resetToDefaultCountry()
            }
            alert.setNegativeButton(R.string.no) { dialog, _ ->
                dialog.cancel()
            }
            alert.setNeutralButton(R.string.cancel) { dialog, _ ->
                dialog.cancel()
            }
            alert.show()
        }
          //Gender
        pickGenderText = view.findViewById(R.id.tvGender)
        var genderIndex: Int? = null
        val genderArray = arrayOf("Male", "Female")
        pickGenderText.setOnClickListener {
            val genderDialog = MaterialAlertDialogBuilder(view.context).apply {
                setTitle("Select your gender : ")
                setNeutralButton("Cancel") { _, _ ->
                    genderIndex = null
                    pickGenderText.text = genderIndex
                }
                setPositiveButton("Ok") { _, _ ->
                    pickGenderText.text = genderIndex?.let { genderArray[it] }
                }
                setSingleChoiceItems(genderArray, -1) { _, which ->
                    genderIndex = which
                }
            }
            genderDialog.show()
        }

    }

}


}
