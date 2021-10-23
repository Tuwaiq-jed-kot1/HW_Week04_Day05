package com.example.mainfragment.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import com.example.mainfragment.R
import com.example.mainfragment.data.model.User

class DetailsFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: DetailsViewModel
    private lateinit var idTextView:TextureView
    private lateinit var fnameTextView:TextureView
    private lateinit var LnameTextView: TextureView
    private lateinit var scoretextView: TextureView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
         requireArguments().getParcelable<User>("userKey").let {
         idTextView=view.findViewById(R.id.tvIdDetailes)
         fnameTextView=view.findViewById(R.id.tvfname)
        fnameTextView=view.findViewById(R.id.tvlname)
        fnameTextView=view.findViewById(R.id.tvscore)

             idTextView="user ID:${it?.id}"
             fnameTextView="frist name:${it?.fName}"
             fnameTextView="last name :${it?.lName}"
             fnameTextView="score:${it?.score}"





    }


}}