package com.sumaya.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class showFragment : MFragment() {
    companion object {
        fun newInstance() = showFragment()
    }
    private lateinit var spinner: TextView
    private lateinit var date: TextView
    private lateinit var phone: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinner = view.findViewById(R.id.tvGender)
        date = view.findViewById(R.id.pickDate)
        phone = view.findViewById(R.id.phone)

        spinner.text = requireArguments().getString("gender")
        date.text = requireArguments().getString("date")
        phone.text = requireArguments().getString("phone")

    }
}
