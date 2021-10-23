package com.sumaya.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class InfoFragment : Fragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    private lateinit var nameText: TextView
    private lateinit var genderText: TextView
    private lateinit var dateText: TextView
    private lateinit var phoneText: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameText = view.findViewById(R.id.txtName); genderText = view.findViewById(R.id.txtGender)
        dateText = view.findViewById(R.id.txtDate); phoneText = view.findViewById(R.id.txtPhone)

        nameText.text = requireArguments().getString("name"); genderText.text = requireArguments().getString("gender")
        dateText.text = requireArguments().getString("date"); phoneText.text = requireArguments().getString("phone")

    }
}

