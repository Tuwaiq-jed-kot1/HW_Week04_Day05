package com.sumaya.myapplication.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.sumaya.myapplication.R


class InfoFragment : Fragment() {

private lateinit var showInfo: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_info, container, false)
        showInfo = view.findViewById(R.id.displayMessage)
        parentFragmentManager.setFragmentResultListener(getString(R.string.hello_blank_fragment),this){
            key, info ->
            showInfo.text = info.getString("data")
        }

return view
        }


    }


