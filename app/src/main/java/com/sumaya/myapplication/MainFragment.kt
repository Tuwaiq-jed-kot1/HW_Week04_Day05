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
import androidx.appcompat.app.AppCompatActivity
import com.hbb20.CountryCodePicker



class MainFragment : Fragment() {
    private lateinit var pickDate: TextView
    private lateinit var ccp: CountryCodePicker
    private var countryCode: String? = null
    private var countryName: String? = null
    private lateinit var showInfo: TextView
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
            alert.setNeutralButton(R.string.cancel) { dialog, which ->
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
        showInfo = view.findViewById(R.id.info)
        phone = view.findViewById(R.id.phone)
        ccp = view.findViewById(R.id.pickCode)
        ccp.setOnCountryChangeListener {
            countryCode = ccp.selectedCountryCode
            countryName = ccp.selectedCountryName
        }

        name = view.findViewById(R.id.txtName)
        send.setOnClickListener {
            val activity = view.context as AppCompatActivity

            var info = "Name: ${name.text}\n"
            info += "Gender: ${gender.text}\n"
            info += "Birthday: $date\n"
            info += "Phone: +${countryCode.toString() + phone.text}\n"

            showInfo.setText(info)
            val input = showInfo.text.toString()
            val bundle = Bundle()
            bundle.putString("data", input)
            val fragment = InfoFragment()
            fragment.arguments = bundle
            activity.supportFragmentManager.beginTransaction().replace(R.id.nav_container, fragment).commit()

        }

        //3. Alert dialog
        clear = view.findViewById(R.id.clear)
        clear.setOnClickListener {
            val alert = AlertDialog.Builder(view.context)
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

        return view
    }


}


