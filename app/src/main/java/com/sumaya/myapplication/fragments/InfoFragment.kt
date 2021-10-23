package com.sumaya.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sumaya.myapplication.R

class InfoFragment: Fragment() {
    private lateinit var showInfo : TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.info_fragment, container, false)
        showInfo = view.findViewById(R.id.info)
        val args = this.arguments
        val inputData = args?.get("sent data")
        showInfo.text = inputData.toString()
        return view
    }
}