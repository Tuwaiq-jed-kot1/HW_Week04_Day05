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
//    private lateinit var ccp: CountryCodePicker
//    private var countryCode:String? = null
//    private var countryName:String? = null
//    private lateinit var showInfo : TextView
//    private lateinit var phone: EditText
//
//    //3. gender
  private lateinit var gender : TextView
//
    private lateinit var send: Button
//    private lateinit var clear : Button
    //private lateinit var date :String

    private lateinit var name: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val name: EditText = view.findViewById(R.id.id_name)


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

                val input = "Name: ${name.text.toString()} \n Gender: ${gender.text.toString()}"

                val bandle = Bundle()//bandle هو عبارة عن حاوية لارسال البيانات
                bandle.putString("data", input)

                val infoFragment = InfoFragment()
                infoFragment.arguments = bandle
                fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, infoFragment)
                    ?.commit()


                /*activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container,infoFragment)
                ?.addToBackStack("details")
                ?.commit()//commitNow*/

                //هنا يتحقق اذا كان الجوال بالعرض يعرض البيانات في الفريم (نفس الصفحه) و اذا  كان الجوال بالطول يروح فارقمينت ثانيه
                /*     val view = R.id.container
                   val bandle =Bundle()
                   val fragment = InfoFragment.newInstance()

                   fragment.arguments  = bandle

                   activity?.supportFragmentManager?.beginTransaction()

                       ?.replace(R.id.fragment_container,fragment)
                       ?.addToBackStack("details")
                       ?.commit()//commitNow*/

            }

            return view
        }

/*  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  super.onViewCreated(view, savedInstanceState)



}*/


    }

