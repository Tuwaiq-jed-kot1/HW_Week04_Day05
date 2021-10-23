package com.sumaya.myapplication.register

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hbb20.CountryCodePicker
import com.sumaya.myapplication.R
import com.sumaya.myapplication.info.InfoFragment
import java.util.*

const val USER_KEY = "USER_KEY"

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var pickDate: TextView

    private lateinit var ccp: CountryCodePicker
    private var countryCode: String? = null
    private var countryName: String? = null

    private lateinit var phone: EditText
    private lateinit var name: EditText

    private lateinit var send: Button
    private lateinit var clear: Button
    private var date: String? = null

    private lateinit var pickGender: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        name = view.findViewById(R.id.name)

        pickDate = view.findViewById(R.id.pickDate)
        calenderDialog(view)

        phone = view.findViewById(R.id.phone)
        ccp = view.findViewById(R.id.pickCode)
        ccpDropDownMenu()

        pickGender = view.findViewById(R.id.pickGender)
        genderDialog(view)

        send = view.findViewById(R.id.send)
        sendButton(view)

        clear = view.findViewById(R.id.clear)
        clearButton(view)

    }

    private fun sendButton(view: View) {
        send.setOnClickListener {
            viewModel.fillInfo(
                name.text.toString(),
                date ?: "",
                pickGender.text.toString(),
                countryCode.toString() + phone.text
            )
            startFragment(view)
        }
    }

    private fun ccpDropDownMenu() {
        countryCode = ccp.defaultCountryCode
        countryName = ccp.defaultCountryName
        ccp.setOnCountryChangeListener {
            countryCode = ccp.selectedCountryCode
            countryName = ccp.selectedCountryName
        }
    }

    private fun calenderDialog(view: View) {

        //create object of Calendar
        val calendar = Calendar.getInstance()
        // add day of month
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        pickDate.setOnClickListener {
            DatePickerDialog(view.context, { _, y, m, d ->
                date = "$d/$m/$y"
                pickDate.text = date
            }, year, month, day)
                .show()
        }
    }

    private fun genderDialog(view: View) {
        pickGender.setOnClickListener {
            val genderList = resources.getStringArray(R.array.Gender)
            val materialAlertDialogBuilder = MaterialAlertDialogBuilder(view.context)
            materialAlertDialogBuilder.setTitle("Choose your Gender:")

            materialAlertDialogBuilder.setSingleChoiceItems(genderList, 0) { dialog, which ->
                pickGender.text = genderList[which]
                dialog.dismiss()
            }
            materialAlertDialogBuilder.setNeutralButton(R.string.cancel) { dialog, _ ->
                dialog.cancel()
            }
            materialAlertDialogBuilder.show()
        }
    }

    private fun clearButton(view: View) {
        clear.setOnClickListener {
            val alert = AlertDialog.Builder(view.context)
            alert.setTitle("Reset")
            alert.setIcon(R.drawable.alert)
            alert.setMessage("Are you sure you want to clear all entries?")
            alert.setPositiveButton(R.string.yes) { dialog, which ->
                name.text = null
                pickGender.text = null
                pickDate.text = null
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

    private fun startFragment(view: View) {
        val fragment = InfoFragment.newInstance()
        val bundle = Bundle()
        bundle.putParcelable(USER_KEY, viewModel.getInfo())
        fragment.arguments = bundle

        val activity = view.context as AppCompatActivity
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack("infoFragment")
            .commit()

    }
}