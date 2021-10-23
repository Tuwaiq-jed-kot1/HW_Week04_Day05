package com.sumaya.myapplication.Fragment

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

        val view =inflater.inflate(R.layout.fragment_info, container, false)

        val textViewName:TextView=view.findViewById(R.id.textview_info)
        val args = this.arguments
        val inputData = args?.get("data")
        textViewName.text =inputData.toString()

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

  /*    if (arguments!=null){




                showInfo=view.findViewById(R.id.textview_info)


               showInfo.text= "name : ${id_name.text}"

            }

*/
        }
    }


