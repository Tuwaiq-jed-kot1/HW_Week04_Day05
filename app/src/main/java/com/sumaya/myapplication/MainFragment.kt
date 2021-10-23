package com.sumaya.myapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.hbb20.CountryCodePicker
import java.util.*

class MainFragment : Fragment() {
    //1. Date
    private lateinit var pickDate: TextView

    //2. Country Code
    private lateinit var ccp: CountryCodePicker
    private var countryCode: String? = null
    private var countryName: String? = null

    // private lateinit var showInfo: TextView
    private lateinit var phone: EditText
    private lateinit var name: EditText
    private lateinit var genderVal: String
    private lateinit var send: Button
    private lateinit var clear: Button
    private lateinit var date: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
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
        //showInfo = view.findViewById(R.id.info)
        phone = view.findViewById(R.id.phone)
        name = view.findViewById(R.id.name)
        ccp = view.findViewById(R.id.pickCode)
        ccp.setOnCountryChangeListener {
            countryCode = ccp.selectedCountryCode
            countryName = ccp.selectedCountryName
        }

        val spinner: Spinner = view.findViewById(R.id.gender)
        ArrayAdapter.createFromResource(
            view.context,
            R.array.genderArr,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                genderVal = spinner.selectedItem.toString()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        }

        //prepare Info
        send = view.findViewById(R.id.send)
        send.setOnClickListener {
            var info = "Name: ${name.text} \nGender: $genderVal\nBirthday: $date\n"
            info += "Phone: +${countryCode.toString() + phone.text}\n "
            //pass Info. to InfoFragment
            parentFragmentManager.setFragmentResult("infoKey", bundleOf("data" to info))
            //replace the fragment directly
            parentFragmentManager.beginTransaction().replace(R.id.FragemtFrame, InfoFragment())
                .commit()

        }

        //3. Alert dialog
        clear = view.findViewById(R.id.clear)
        clear.setOnClickListener {
            val alert = AlertDialog.Builder(view.context)
            alert.setTitle("Reset")
            alert.setIcon(R.drawable.alert)
            alert.setMessage("Are you sure you want to clear all entries?")
            alert.setPositiveButton(R.string.yes) { dialog, which ->
                name.text = null
                pickDate.text = null
                phone.text = null
                //showInfo.text = null
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
        return view
    }


}

