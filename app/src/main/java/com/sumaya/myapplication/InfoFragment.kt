package com.sumaya.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class InfoFragment : Fragment() {
    private lateinit var showInfo: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_info, container, false)
        showInfo = view.findViewById(R.id.info)

        //receive Info.
        parentFragmentManager.setFragmentResultListener("MyKey",this){key, info->
            showInfo.text=  info.getString("data")
        }
        return view
    }


}