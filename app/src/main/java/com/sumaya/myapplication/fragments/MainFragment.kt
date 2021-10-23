package com.sumaya.myapplication.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.hbb20.CountryCodePicker
import com.sumaya.myapplication.MainActivity
import com.sumaya.myapplication.R
import java.util.*

class MainFragment : Fragment() {
    //0. Name
    private lateinit var name: EditText
    //1. Date
    private lateinit var pickDate : TextView
    //2. Country Code
    private lateinit var ccp: CountryCodePicker
    private var countryCode:String? = null
    private var countryName:String? = null
//    private lateinit var showInfo : TextView //
    private lateinit var phone: EditText
    //3. Gender Spinner
    private lateinit var spinner1gender: Spinner
    private val gender: Array<String> = arrayOf("Male", "Female", "I'd rather not say")
    private var genderSelected: String = ""
    private lateinit var send: Button
    private lateinit var clear : Button
    private lateinit var date :String

    //private lateinit var activity: MainActivity //tried this way, didn't work.


    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            //0. Name Edit Text
            //name = findViewById(R.id.editText_Name)
            name = view.findViewById(R.id.editText_Name)

            // 1. Date Dialog
            //pickDate = findViewById(R.id.pickDate)
            pickDate = view.findViewById(R.id.pickDate)

            //create object of Calendar
            val calendar = Calendar.getInstance()
            // add day of month
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            pickDate.setOnClickListener {
                activity?.let { it1 ->
                    DatePickerDialog(it1, DatePickerDialog.OnDateSetListener{ _, y, m, d ->
                        date = "$d/$m/$y"
                        pickDate.setText(date)
                    },year,month, day)
                        .show()
                }
            }

/*            pickDate.setOnClickListener {
                DatePickerDialog(activity, DatePickerDialog.OnDateSetListener{ _, y,m,d ->
                    date = "$d/$m/$y"
                    pickDate.setText(date)
                },year,month, day)
                    .show()
            }*/

            //2. Country Code
//            showInfo = findViewById(R.id.info)
//            phone = findViewById(R.id.phone)
//            ccp = findViewById(R.id.pickCode)

//            showInfo = view.findViewById(R.id.info)
            phone = view.findViewById(R.id.phone)
            ccp = view.findViewById(R.id.pickCode)
            ccp.setOnCountryChangeListener {
                countryCode = ccp.selectedCountryCode
                countryName = ccp.selectedCountryName
            }

            //3. Gender Spinner
            //spinner1gender = findViewById(R.id.spinner1_gender)
            spinner1gender = view.findViewById(R.id.spinner1_gender)
            //val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, gender)
            val arrayAdapter = activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, gender) }
            spinner1gender.adapter = arrayAdapter
            spinner1gender.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Toast.makeText(activity,  "You selected ${gender[position]}", Toast.LENGTH_SHORT).show()
                    genderSelected = gender[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Toast.makeText(activity,  "No selection made", Toast.LENGTH_SHORT).show()
                }

            }

            //show Info
//            send = findViewById(R.id.send)
            send = view.findViewById(R.id.send)
            send.setOnClickListener {
                val bundle = Bundle()
                var info = "Sent information is as follows:\n"
                info += "Name: ${name.text}\n"
                info += "Birthday: $date\n"
                info += "Phone: +${countryCode.toString()+ phone.text}\n"
                info += "Gender: $genderSelected"
                //showInfo.setText(info)
                bundle.putString("sent data", info)
                val infoFragment = InfoFragment()
                infoFragment.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(R.id.container, infoFragment)?.commit()
            }

            //4. Alert dialog
//            clear= findViewById(R.id.clear)
            clear = view.findViewById(R.id.clear)
            clear.setOnClickListener {
                val alert = AlertDialog.Builder(activity)
                alert.setTitle("Reset")
                alert.setIcon(R.drawable.alert)
                alert.setMessage("Are you sure you want to clear all entries?")
                alert.setPositiveButton(R.string.yes) { dialog, which ->
                    name.setText(null)
                    pickDate.setText(null)
                    phone.setText(null)
                    //showInfo.setText(null)
                    ccp.resetToDefaultCountry()
                    //arrayAdapter.set
                    //spinner1gender.resetPivot()
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