package com.sumaya.myapplication.Fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.marginStart
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hbb20.CountryCodePicker

import com.sumaya.myapplication.R


class MainFragment : Fragment() {



    //1. Date
    private lateinit var pickDate: TextView

    //2. Country Code
    private lateinit var ccp: CountryCodePicker
    private var countryCode: String? = null
    private var countryName: String? = null
    private lateinit var showInfo: TextView
    private lateinit var phone: EditText

    //3. Gender
    private lateinit var pickGender: TextView

    private lateinit var send: Button
    private lateinit var clear: Button
    private lateinit var date: String
    private lateinit var name: EditText


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

         name = view.findViewById(R.id.fName)

        // 1. Date Dialog
        pickDate = view.findViewById(R.id.pickDate)

        //create object of Calendar
        val calendar = Calendar.getInstance()
        // add day of month
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        pickDate.setOnClickListener {
            DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener { _, y, m, d ->
                date = "$d/$m/$y"
                pickDate.setText(date)
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
        send = view.findViewById(R.id.btnSend)
        send.setOnClickListener {
            var info = "Name: $name\n Birthday: $date\n"
            info += "Phone: +${countryCode.toString() + phone.text}\n"
            showInfo.setText(info)

        }


        //3. Alert dialog
        clear = view.findViewById(R.id.btnClear)
        clear.setOnClickListener {
            val alert = AlertDialog.Builder(view.context)
            alert.setTitle("Reset")
            alert.setIcon(R.drawable.alert)
            alert.setMessage("Are you sure you want to clear all entries?")
            alert.setPositiveButton(R.string.yes) { dialog, which ->
                pickDate.setText(null)
                phone.setText(null)
                showInfo.setText(null)
                pickGender.setText(null)
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

        //4. Gender
        pickGender = view.findViewById(R.id.genderPicker)
        var selectedItemIndex : Int? = null
        val arrItems = arrayOf("Male", "Female")
        pickGender.setOnClickListener {
             MaterialAlertDialogBuilder(view.context).apply {
                setTitle("Please choose your gender:")
                setPositiveButton("Ok") { _, _ ->
                    pickGender.text = selectedItemIndex?.let { arrItems[it] }
                    }

                setSingleChoiceItems(arrItems, -1 ) { _, which ->
                    selectedItemIndex = which
                }

                }.show()


        }

return view
    }
}


