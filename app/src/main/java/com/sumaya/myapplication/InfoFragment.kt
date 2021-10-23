package com.sumaya.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sumaya.myapplication.R


class InfoFragment : Fragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =inflater.inflate(R.layout.fragment_info, container, false)
        val textView:TextView=view.findViewById(R.id.data_info)
        val args = this.arguments
        val inputData = args?.get("data")
        textView.text =inputData.toString()

        return view

    }

}