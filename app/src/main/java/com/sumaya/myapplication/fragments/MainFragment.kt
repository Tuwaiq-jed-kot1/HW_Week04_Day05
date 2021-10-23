package com.sumaya.myapplication.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.hbb20.CountryCodePicker
import com.sumaya.myapplication.R
import java.util.*
import kotlin.collections.ArrayList


class MainFragment : Fragment() {

    private lateinit var name : EditText
    //1. Date
    private lateinit var pickDate : TextView
    private lateinit var ccp: CountryCodePicker
    private var countryCode:String? = null
    private var countryName:String? = null
    private lateinit var phone: EditText
    private lateinit var send: Button
    private lateinit var clear : Button
    private lateinit var date :String
    private lateinit var infoArray :ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //name
        name=view.findViewById(R.id.name)

        //gender dropdown menue
        val gender= resources.getStringArray(R.array.gender)
        val adapter = ArrayAdapter(requireContext(),R.layout.gender_list,gender)
        val autoco=view.findViewById<AutoCompleteTextView>(R.id.gender)
        autoco.setAdapter(adapter)

        // Date Dialog
        pickDate= view.findViewById(R.id.pickDate)
        //create object of Calendar
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

        //2. Country Code

        phone = view.findViewById(R.id.phone)

        ccp = view.findViewById(R.id.pickCode)
        ccp.setOnCountryChangeListener {
            countryCode = ccp.selectedCountryCode
            countryName = ccp.selectedCountryName

        }


        val activity = view.context as AppCompatActivity
        send = view.findViewById(R.id.send)
        send.setOnClickListener {
            val bundle= Bundle()
            infoArray= arrayListOf(name.text.toString(), autoco.text.toString(),date,phone.text.toString())
            bundle.putStringArrayList("key",infoArray)
            val frag =InfoFragment()
            frag.arguments=bundle
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.container, frag )
                .addToBackStack("first")
                .commit()
        }

        //3. Alert dialog
        clear= view.findViewById(R.id.clear)
        clear.setOnClickListener {
            val alert = AlertDialog.Builder(requireContext())
            alert.setTitle("Reset")
            alert.setIcon(R.drawable.alert)
            alert.setMessage("Are you sure you want to clear all entries?")
            alert.setPositiveButton(R.string.yes) { dialog, which ->
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