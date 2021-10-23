package com.hani.navigationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val submitBtn: Button = view.findViewById(R.id.submit)
        submitBtn.setOnClickListener {
            val editText : EditText = view.findViewById(R.id.editText)
            val input = editText.text.toString()
            val bundle = Bundle()
            bundle.putString("data",input)
            val fragment = InfoFragment()
            fragment.arguments = bundle
            fragmentManager?.beginTransaction()?.replace(R.id.nav_container , fragment)?.commit()

        }
        return view
    }
}