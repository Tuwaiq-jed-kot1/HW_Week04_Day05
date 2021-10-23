package com.sumaya.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class InfoFragment : Fragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }


    private lateinit var showInfo: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showInfo= view.findViewById(R.id.info)

        var mainFragment = MainFragment()
        val args = this.arguments
        val infoData = args?.get("info")
        showInfo.text = infoData.toString()
    }
}