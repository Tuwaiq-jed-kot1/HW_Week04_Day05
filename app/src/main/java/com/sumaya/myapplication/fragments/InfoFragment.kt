package com.sumaya.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sumaya.myapplication.R


class InfoFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }
    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val showInfo :TextView = view.findViewById(R.id.info)
        val args= this.arguments
        val userInfo :ArrayList<String>? =args?.getStringArrayList("key")
      val info=" name: ${userInfo?.get(0)}\n gender: ${userInfo?.get(1)}" +
                "\n birthday: ${userInfo?.get(2)}\n phone number: ${userInfo?.get(3)}"
        showInfo.text=info
    }


}