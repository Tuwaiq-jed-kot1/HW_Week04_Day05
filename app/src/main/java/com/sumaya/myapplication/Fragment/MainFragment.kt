package com.sumaya.myapplication.Fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import com.hbb20.CountryCodePicker
import com.sumaya.myapplication.R


class MainFragment : Fragment() {
    private lateinit var ccp: CountryCodePicker
    private var countryCode: String? = null
    private var countryName: String? = null
    private lateinit var pickDate: TextView
    private lateinit var date: String
    private lateinit var txtEmail: TextView
    private lateinit var clearbtn: Button


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val sendButton: Button = view.findViewById(R.id.send)

        txtEmail = view.findViewById(R.id.email)
        clearbtn = view.findViewById(R.id.clear)

        // birthday
        pickDate = view.findViewById(R.id.pickDate12)
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


        //Phone Number
        val etPhone: EditText = view.findViewById(R.id.phone)
        ccp = view.findViewById(R.id.pickCode)
        var countryCode = ""
        ccp.setOnCountryChangeListener {
            countryCode = ccp.selectedCountryCode
            countryName = ccp.selectedCountryName
        }

        //Gender
        val sGender: TextView = view.findViewById(R.id.id_gender)
        sGender.setOnClickListener {
            val listItems = arrayOf("Male", "Female")
            val mBuilder = AlertDialog.Builder(view.context)
            mBuilder.setTitle("Please choose your gender :")
            mBuilder.setSingleChoiceItems(listItems, -1) { dialogInterface, i ->
                sGender.text = listItems[i]
                dialogInterface.dismiss()
            }

            mBuilder.setNeutralButton("Cancel") { dialog, which ->
                dialog.cancel()
            }
            val mDialog = mBuilder.create()
            mDialog.show()
        }

        //clear
        clearbtn = view.findViewById(R.id.clear)
        clearbtn.setOnClickListener {
            val alert = AlertDialog.Builder(view.context)
            alert.setTitle("Reset")
            alert.setIcon(R.drawable.alert)
            alert.setMessage("Are you sure you want to clear all entries?")
            alert.setPositiveButton(R.string.yes) { dialog, which ->
                pickDate.setText(null)
                etPhone.setText(null)
                ccp.resetToDefaultCountry()
                txtEmail.setText(null)
                sGender.setText(null)
            }
            alert.setNegativeButton(R.string.no) { dialog, which ->
                dialog.cancel()
            }
            alert.setNeutralButton(R.string.cancel) { dialog, which ->
                dialog.cancel()
            }
            alert.show()
        }


            //send
            sendButton.setOnClickListener {

                if (txtEmail != null && pickDate.text.isNotEmpty() && sGender.text.isNotEmpty() && etPhone.text.isNotEmpty()) {

                    val cullectInfo = "you'r Information are:- \n " +
                            "\n Email: ${txtEmail.text} \n" +
                            "BirthDay: ${date} \n" +
                            "Phone number: ${countryCode + etPhone.text} \n" +
                            "Gender: ${sGender.text}"

                    val bundle = Bundle()
                    bundle.putString("data", cullectInfo)
                    val tranToInfoFrag = InfoFragment()
                    tranToInfoFrag.arguments = bundle
                    fragmentManager?.beginTransaction()
                        ?.replace(R.id.fragment_container, tranToInfoFrag)
                        ?.addToBackStack("back")
                        ?.commit()
                } else {
                    Toast.makeText(context, "complete you'r information", Toast.LENGTH_SHORT).show()
                }
            }

            return view
        }
    }
