package com.sumaya.myapplication.Fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.hbb20.CountryCodePicker
import com.sumaya.myapplication.R
import java.util.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    //    //1. Date
    private lateinit var pickDate: TextView

    //
//    //2. Country Code
    private lateinit var ccp: CountryCodePicker
    private var countryCode:String? = null
    private var countryName:String? = null
    private lateinit var showInfo : TextView
    private lateinit var phone: EditText

    //3. gender
    private lateinit var gender: TextView

    private lateinit var send: Button
  private lateinit var clear : Button
    private lateinit var date :String

    private lateinit var name: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val name: EditText = view.findViewById(R.id.id_name)


// 1. Date Dialog
        pickDate =view.findViewById(R.id.pickDate)

        //create object of Calendar
        val calendar = Calendar.getInstance()
        // add day of month
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        pickDate.setOnClickListener {
            DatePickerDialog(this.requireContext(), DatePickerDialog.OnDateSetListener { _, y, m, d ->
                date = "$d/$m/$y"
                pickDate.setText(date)
            }, year, month, day)
                .show()
        }




        //2. Country Code
        showInfo =view.findViewById(R.id.info)
        phone =view.findViewById(R.id.phone)
        ccp =view.findViewById(R.id.pickCode)
        ccp.setOnCountryChangeListener {
            countryCode = ccp.selectedCountryCode
            countryName = ccp.selectedCountryName
        }

        gender = view.findViewById(R.id.id_gender)
        gender.setOnClickListener {
            val alert_gender = AlertDialog.Builder(context)
            alert_gender.setTitle("Gender")

            alert_gender.setMessage("Please choose your gender")

            alert_gender.setPositiveButton("Male") { dialog, which ->
                gender.setText("Male")
            }

            alert_gender.setNegativeButton("Fmale") { dialog, which ->
                gender.setText("Fmale")
            }

            alert_gender.setNeutralButton(R.string.cancel) { dialog, which ->
                dialog.cancel()
            }.show()
        }


        val btnSend: Button = view.findViewById(R.id.send)

        btnSend.setOnClickListener {
            if (name!=null && date.isNotEmpty() && gender.text.isNotEmpty() && phone.text.isNotEmpty()){

                val input = "Name: ${name.text.toString()} \n BirthDay :${date.toString()} \n Gender: ${gender.text.toString()}" +
                        "\n ${showInfo.text.toString()} \n Phone:${countryCode.toString()+ phone.text}"

                val bandle = Bundle()//bandle هو عبارة عن حاوية لارسال البيانات
                bandle.putString("data", input)

                val infoFragment = InfoFragment()
                infoFragment.arguments = bandle
                fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, infoFragment)
                    ?.commit()
            }else{


                Toast.makeText(context,"vbnm,",Toast.LENGTH_SHORT).show()
            }


        }




        //3. Alert dialog
        clear= view.findViewById(R.id.clear)
        clear.setOnClickListener {

            val alert = AlertDialog.Builder(context)
            alert.setTitle("Reset")
            alert.setIcon(R.drawable.alert)
            alert.setMessage("Are you sure you want to clear all entries?")
            alert.setPositiveButton(R.string.yes) { dialog, which ->
                pickDate.setText(null)
                phone.setText(null)
                showInfo.setText(null)
                gender.setText(null)
                name.setText(null)
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

/*  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
super.onViewCreated(view, savedInstanceState)



}*/


}

