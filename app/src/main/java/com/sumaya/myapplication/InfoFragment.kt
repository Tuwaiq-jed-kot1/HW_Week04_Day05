package com.sumaya.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class InfoFragment : Fragment() {
    private lateinit var TextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_info,container,false)
       TextView=view.findViewById(R.id.showInfo)
        val arg=this.arguments
        val inputData=arg?.get("data")
        TextView.text=inputData.toString()
        return view


    }
}