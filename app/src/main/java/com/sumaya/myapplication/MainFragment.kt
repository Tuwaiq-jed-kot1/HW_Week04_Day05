package com.sumaya.myapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.hbb20.CountryCodePicker
import java.util.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.AdapterView.OnItemSelectedListener


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    //1. Date
    private lateinit var pickDate: TextView

    //2. Country Code
    private lateinit var ccp: CountryCodePicker
    private var countryCode: String? = null
    private var countryName: String? = null
    private lateinit var showInfo: TextView
    private lateinit var phone: EditText
    private lateinit var send: Button
    private lateinit var clear: Button
    private  var date: String=""
    private lateinit var resultSp: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

         resultSp = view.findViewById(R.id.resultSp)
        val gender = arrayOf("male", "female")
        val myAdapter =
            ArrayAdapter<String>(view.context, android.R.layout.simple_spinner_item, gender)
        var spinner = view.findViewById<Spinner>(R.id.spinner)
        spinner.adapter = myAdapter
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                resultSp.text = gender[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }


        // 1. Date Dialog
        pickDate = view.findViewById(R.id.pickDate)

        //create object of Calendar
        val calendar = Calendar.getInstance()
        // add day of month
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        pickDate.setOnClickListener {
            DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener { _, y, m, d ->
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

        //show Info
        send = view.findViewById(R.id.send)
        send.setOnClickListener {
            var info = "Birthday: $date\n gender: ${resultSp.text}"
            info += "Phone: +${countryCode.toString() + phone.text}\n"
            showInfo.setText(info)
            val bundle = Bundle()
            bundle.putString("info", info)
            val fragment = InfoFragment.newInstance()
            fragment.arguments = bundle
            val activity = view.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                .addToBackStack("infoPage")
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


}