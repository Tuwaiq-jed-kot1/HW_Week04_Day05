package com.sumaya.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class InfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     val view= inflater.inflate(R.layout.fragment_info, container, false)
        val Showinfo:TextView=view.findViewById(R.id.showInfo)
        val args =this.arguments
        val inputData=args?.get("phone")
        Showinfo.text=inputData.toString()


      return view
    }

}