package com.sumaya.myapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hbb20.CountryCodePicker
import java.util.*


class MainFragment : Fragment() {
    //Name
    private lateinit var name: EditText
    //Gender
    private lateinit var gender : TextView

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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Gender Dialog
        gender= view.findViewById(R.id.gender)
        gender.setOnClickListener {

            val alert = AlertDialog.Builder(this.context)
            alert.setTitle("Choose your gender")
            val array = arrayOf("Male", "Female")
            alert.setSingleChoiceItems(array, -1) { _, which ->
                val selectedGender = array[which]

                gender.setText(array[which])
            }
            alert.setNeutralButton(R.string.cancel) { dialog, which ->
                dialog.cancel()
                gender.setText("---")
            }
            alert.show()
        }


        // 1. Date Dialog
        pickDate= view.findViewById(R.id.pickDate)

        //create object of Calendar
        val calendar = Calendar.getInstance()
        // add day of month
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        pickDate.setOnClickListener {
            context?.let { it1 ->
                DatePickerDialog(it1, DatePickerDialog.OnDateSetListener{ _, y, m, d ->
                    date = "$d/${m+1}/$y"
                    pickDate.setText(date)
                },year,month, day)
                    .show()
            }
        }

        //2. Country Code
        phone = view.findViewById(R.id.phone)
        ccp = view.findViewById(R.id.pickCode)
        ccp.setOnCountryChangeListener {
            countryCode = ccp.selectedCountryCode
            countryName = ccp.selectedCountryName
        }

        //show Info
        name = view.findViewById(R.id.Name)
        send = view.findViewById(R.id.send)
        send.setOnClickListener {
            val activity = view.context as AppCompatActivity

            var info = "Name: ${name.text}\n"
            info += "Gender: ${gender.text}\n"
            info += "Birthday: $date\n"
            info += "Phone: +${countryCode.toString()+ phone.text}\n"


            val infoFragment = InfoFragment()
            val bundle = Bundle()
            bundle.putString("info",info)
            infoFragment.arguments = bundle

            activity.supportFragmentManager.beginTransaction().replace(R.id.container,infoFragment)
                .commit()
        }

        //3. Alert dialog
        clear= view.findViewById(R.id.clear)
        clear.setOnClickListener {
            val alert = AlertDialog.Builder(this.context)
            alert.setTitle("Reset")
            alert.setIcon(R.drawable.alert)
            alert.setMessage("Are you sure you want to clear all entries?")
            alert.setPositiveButton(R.string.yes) { dialog, which ->
                name.setText(null)
                gender.setText(null)
                pickDate.setText(null)
                phone.setText(null)
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
    }
