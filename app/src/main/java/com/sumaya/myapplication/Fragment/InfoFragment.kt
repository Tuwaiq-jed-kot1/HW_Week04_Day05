package com.sumaya.myapplication.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.sumaya.myapplication.Data_Info
import com.sumaya.myapplication.R


class InfoFragment : Fragment() {

    companion object {
       // fun newInstance() = Details()
    }
    private lateinit var showInfo:TextView

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments!=null){


            requireArguments().getParcelable<Data_Info>("KD").let {

                showInfo=view.findViewById(R.id.textview_info)


               showInfo.text= "name : ${it?.name}"

            }


        }
    }


    }