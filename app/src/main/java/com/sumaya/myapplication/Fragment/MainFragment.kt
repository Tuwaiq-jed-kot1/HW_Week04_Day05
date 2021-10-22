package com.sumaya.myapplication.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sumaya.myapplication.R


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var send: Button

    private lateinit var name: EditText




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val btnSend:Button = view.findViewById(R.id.send)
        val name:EditText = view.findViewById(R.id.id_name)

        btnSend.setOnClickListener {
            val input = name.text.toString()




            val bandle =Bundle()//bandle هو عبارة عن حاوية لارسال البيانات
            bandle.putString("KD",input)
            //ممكن eeror
            val infoFragment =InfoFragment()
            infoFragment.arguments =bandle
            Toast.makeText(context , " ${name.text}", Toast.LENGTH_SHORT).show()

            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container,infoFragment)
                ?.addToBackStack("details")
                ?.commit()//commitNow


            //هنا يتحقق اذا كان الجوال بالعرض يعرض البيانات في الفريم (نفس الصفحه) و اذا  كان الجوال بالطول يروح فارقمينت ثانيه
         /*   val view = R.id.container
            val fragment = InfoFragment.newInstance()

            fragment.arguments  = bandle

            activity?.supportFragmentManager?.beginTransaction()

                ?.replace(R.id.fragment_container,fragment)
                ?.addToBackStack("details")
                ?.commit()//commitNow*/

        }

    }

}

