package com.sumaya.myapplication.fragmant

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import com.hbb20.CountryCodePicker
import com.sumaya.myapplication.R
import kotlinx.android.synthetic.main.fragment_info.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.util.*


class MainFragment : Fragment() {

    private lateinit var pickDate: TextView


    private lateinit var ccp: CountryCodePicker
    private var countryCode: String? = null
    private var countryName: String? = null

    private lateinit var phone: EditText
    private lateinit var name: EditText
    private lateinit var genderVal: String
    private lateinit var send: Button
    private lateinit var clear: Button
    private lateinit var date: String
    var gendar: Array<String> = arrayOf("Male", "Female")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate Step
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

        //showInfo = view.findViewById(R.id.info)
        phone = view.findViewById(R.id.phone)
        name = view.findViewById(R.id.tvName)
        ccp = view.findViewById(R.id.pickCode)
        ccp.setOnCountryChangeListener {
            countryCode = ccp.selectedCountryCode
            countryName = ccp.selectedCountryName
        }



        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, gendar)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                print(gendar)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        //4. Alert dialog
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

        //5. Send Information
        send = view.findViewById(R.id.send)

        send.setOnClickListener {
            var info = "Name: ${name.text}\n"
            info += "\nBirthday: $date\n"
            info += "\nGender: $genderVal\n"
            info += "\nPhone: +${countryCode.toString()} ${phone.text}\n "

            //Fragment Manager
            parentFragmentManager.setFragmentResult(
                getString(R.string.Key_All_Info),
                bundleOf("data" to info)
            )
            parentFragmentManager.beginTransaction().replace(R.id.frameFragemt, InfoFragment())
                .commit()
        }
        return view
    }
}


