package com.sumaya.myapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hbb20.CountryCodePicker
import java.util.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    //1. Date
    private lateinit var pickDate : TextView

    //2. Country Code
    private lateinit var ccp: CountryCodePicker
    private var countryCode:String? = null
    private var countryName:String? = null
    private lateinit var phone: EditText

    private lateinit var send: Button
    private lateinit var clear : Button
    private lateinit var date :String

    private lateinit var chooseGender: TextView
    private val genders = arrayOf("Male", "Female")
    private var selectedGender = "Male"

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
            DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener{ _, y, m, d ->
                date = "$d/$m/$y"
                pickDate.setText(date)
            },year,month, day)
                .show()
        }

        chooseGender = view.findViewById(R.id.chooseGender)
        chooseGender.setOnClickListener {
            val genderAlert = AlertDialog.Builder(view.context)
            genderAlert.setTitle("Choose your gender")
            genderAlert.setSingleChoiceItems(genders,0,
                DialogInterface.OnClickListener { dialogInterface, i ->
                selectedGender = genders[i]
                dialogInterface.cancel()
            })
            genderAlert.setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
            })
            genderAlert.show()
            chooseGender.text = selectedGender
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
            var info = "User Information:\n\nBirthday: $date\n"
            info += "Gender: $selectedGender\n"
            info += "Phone: +${countryCode.toString()+ phone.text}\n"

            val bundleInfo = Bundle()
            bundleInfo.putString("Info", info)

            val fragment = InfoFragment.newInstance()
            fragment.arguments = bundleInfo

            val mainActivity = view.context as AppCompatActivity

            mainActivity.supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack("back")
                .commit()

        }

        //3. Alert dialog
        clear= view.findViewById(R.id.clear)
        clear.setOnClickListener {
            val alert = AlertDialog.Builder(view.context)
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