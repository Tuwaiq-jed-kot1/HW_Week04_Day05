package com.sumaya.myapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.hbb20.CountryCodePicker
import java.util.*

class MainFragment : Fragment() {
    //1. Date
    private lateinit var pickDate : TextView

    //2. Country Code
    private lateinit var ccp: CountryCodePicker
    private var countryCode:String? = null
    private var countryName:String? = null
    private lateinit var showInfo : TextView
    private lateinit var phone: EditText
    private lateinit var pickGender: TextView
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText

    private lateinit var send: Button
    private lateinit var clear : Button
    private lateinit var date :String



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)
        etName= view.findViewById(R.id.etName)
        etEmail= view.findViewById(R.id.email)


        // 1. Date Dialog
        pickDate= view.findViewById(R.id.pickDate)

        //create object of Calendar
        val calendar = Calendar.getInstance()
        // add day of month
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        pickDate.setOnClickListener {
            DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener{ _, y,m,d ->
                date = "$d/$m/$y"
                pickDate.setText(date)
            },year,month, day)
                .show()
        }

        pickGender= view.findViewById(R.id.pickGender)
        pickGender.setOnClickListener {
            val listItems = arrayOf("Male", "Female")
            val mBuilder = AlertDialog.Builder(view.context)
            mBuilder.setTitle("Please choose your gender :")
            mBuilder.setSingleChoiceItems(listItems, -1) { dialogInterface, i ->
                pickGender.text = listItems[i]
                dialogInterface.dismiss()
            }
            mBuilder.setNeutralButton("Cancel") { dialog, which ->
                dialog.cancel()
            }
            val mDialog = mBuilder.create()
            mDialog.show()
        }

        //2. Country Code
        phone = view.findViewById(R.id.phone)
        ccp = view.findViewById(R.id.pickCode)
        ccp.setOnCountryChangeListener {
            countryCode = ccp.selectedCountryCode
            countryName = ccp.selectedCountryName
        }

        //show Info
        send = view.findViewById(R.id.send)
        send.setOnClickListener {
            if (etEmail.text.isNotEmpty() && pickDate.text.isNotEmpty() && pickGender.text.isNotEmpty() && phone.text.isNotEmpty()) {

                val cullectInfo = "you'r Information are:- \n" +
                        "Name: ${etName.text} \n"+
                        "Email: ${etEmail.text} \n"+
                        "BirthDay: $date \n" +
                        "Phone number: ${countryCode + phone.text} \n" +
                        "Gender: ${pickGender.text}"

                val bundle = Bundle()
                bundle.putString("data", cullectInfo)
                val tranToInfoFrag = InfoFragment()
                tranToInfoFrag.arguments = bundle
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.container, tranToInfoFrag)
                    ?.addToBackStack("back")
                    ?.commit()
            } else {
                Toast.makeText(context, "complete your information", Toast.LENGTH_SHORT).show()
            }
        }

        //3. Alert dialog
        clear= view.findViewById(R.id.clear)
        clear.setOnClickListener {
            val alert = AlertDialog.Builder(view.context)
            alert.setTitle("Reset")
            alert.setIcon(R.drawable.alert)
            alert.setMessage("Are you sure you want to clear all entries?")
            alert.setPositiveButton(R.string.yes) { dialog, which ->
                etName.setText(null)
                etEmail.setText(null)
                pickDate.setText(null)
                phone.setText(null)
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
        return view
    }
}