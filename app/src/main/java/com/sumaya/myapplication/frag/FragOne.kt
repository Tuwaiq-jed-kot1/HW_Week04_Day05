package com.sumaya.myapplication.frag

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.hbb20.CountryCodePicker
import com.sumaya.myapplication.R
import java.util.*

class FragOne : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val rootView = inflater.inflate(R.layout.fragment_frag_one, container, false)
            return rootView  }
            private lateinit var pickDate : TextView
            private lateinit var ccp: CountryCodePicker
            private var countryCode:String? = null
            private var countryName:String? = null
            private lateinit var showInfo : TextView
            private lateinit var phone: EditText
            private lateinit var spinner: ListView
            private lateinit var send: Button
            private lateinit var clear : Button
            private lateinit var date :String

    companion object {
        fun newInstance() = FragOne()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        pickDate = view.findViewById(R.id.pickDate)
spinner =view.findViewById(R.id.spinner)
        //create object of Calendar
        val genderList = resources.getStringArray(R.array.gender)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, genderList)
            spinner.adapter = adapter
            val calendar = Calendar.getInstance()
            // add day of month
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            pickDate.setOnClickListener {
                DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener{ _, y, m, d ->
                    date = "$d/$m/$y"
                    pickDate.setText(date)
                },year,month, day)
                    .show()
            }


            showInfo = view.findViewById(R.id.info)
            phone = view.findViewById(R.id.phone)
            ccp = view.findViewById(R.id.pickCode)
            ccp.setOnCountryChangeListener {
                countryCode = ccp.selectedCountryCode
                countryName = ccp.selectedCountryName
            }

            send = view.findViewById(R.id.send)
            send.setOnClickListener {
                var info = "Birthday: $date\n"
                info += "Phone: +${countryCode.toString()+ phone.text}\n"
                showInfo.setText(info)
            }

            clear= view.findViewById(R.id.clear)
            clear.setOnClickListener {
                val alert = AlertDialog.Builder(requireContext())
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
    }}

