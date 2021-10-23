package com.sumaya.myapplication.fragmant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sumaya.myapplication.R
import kotlinx.android.synthetic.main.fragment_info.view.*

class InfoFragment : Fragment() {

    private lateinit var showInfo: TextView
var displayMessage: String? =""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_info, container, false)
        showInfo = view.findViewById(R.id.info)

        //receive Info.
        parentFragmentManager.setFragmentResultListener(
            getString(R.string.Key_All_Info),
            this
        ) { key, info ->
            showInfo.text = info.getString("data")
        }

        return view

    }}