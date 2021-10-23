package com.sumaya.myapplication.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sumaya.myapplication.R
import com.sumaya.myapplication.data.model.UserInfo
import com.sumaya.myapplication.register.USER_KEY


class InfoFragment : Fragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }

    private lateinit var name: TextView
    private lateinit var gender: TextView
    private lateinit var phone: TextView
    private lateinit var bDay: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            requireArguments().getParcelable<UserInfo>(USER_KEY).let {
                name = view.findViewById(R.id.nameInfo)
                gender = view.findViewById(R.id.GenderInfo)
                phone = view.findViewById(R.id.phoneInfo)
                bDay = view.findViewById(R.id.bDayInfo)

                name.text = "Name: ${it?.name}"
                gender.text = "Gender: ${it?.gender}"
                phone.text = "Phone: ${it?.phone}"
                bDay.text = "BirthDate: ${it?.bDay}"

            }
        }
    }
}