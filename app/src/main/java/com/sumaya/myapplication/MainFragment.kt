package com.sumaya.myapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.icu.util.Calendar

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import com.hbb20.CountryCodePicker



class MainFragment : Fragment() {
    private var countryCode: String? = null
    private var countryName: String? = null
    private lateinit var pickDate: TextView
    private lateinit var ccp: CountryCodePicker

    private lateinit var phone: EditText
    private lateinit var gender: TextView
    private lateinit var send: Button
    private lateinit var clear: Button
    private lateinit var date: String
    private lateinit var name: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)


        pickDate = view.findViewById(R.id.pickDate)
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        gender = view.findViewById(R.id.pickGender)
        gender.setOnClickListener {

            val alert = AlertDialog.Builder(this.context)
            alert.setTitle("Choose your gender")
            val array = arrayOf("Male", "Female")
            alert.setSingleChoiceItems(array, -1) { _, which ->
                val selectedGender = array[which]

                gender.setText(selectedGender)
            }
            alert.setNeutralButton(R.string.cancel) { dialog, _ ->
                dialog.cancel()
                gender.setText("---")
            }

            alert.show()
        }
        pickDate.setOnClickListener {

            DatePickerDialog(view.context, { _, y, m, d ->
                date = "$d/$m/$y"
                pickDate.setText(date)

            }, year, month, day)
                .show()
        }

        //2. Country Code
       // showInfo = view.findViewById(R.id.info)
        phone = view.findViewById(R.id.phone)
        ccp = view.findViewById(R.id.pickCode)
        ccp.setOnCountryChangeListener {
            countryCode = ccp.selectedCountryCode
            countryName = ccp.selectedCountryName
        }

        name = view.findViewById(R.id.txtName)
        send = view.findViewById(R.id.send)
    send.setOnClickListener{


val phone:EditText=phone.findViewById(R.id.phone)
        val input=phone.text.toString()

        val bundle=Bundle()
        bundle.putString("phone",input)
        val fragment=InfoFragment()
        fragment.arguments=bundle

        this.fragmentManager?.beginTransaction()?.replace(R.id.nav_container,fragment)?.commit()




    }


        //3. Alert dialog
        clear = view.findViewById(R.id.clear)
        clear.setOnClickListener {
            val alert = AlertDialog.Builder(view.context)
            alert.setTitle("Reset")
            alert.setIcon(R.drawable.alert)
            alert.setMessage("Are you sure you want to clear all entries?")
            alert.setPositiveButton(R.string.yes) { _, _ ->
                pickDate.setText(null)
                phone.setText(null)
                gender.setText(null)
                name.setText(null)
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

        return view
    }


}


