package com.example.mainfragment.data.model

import android.os.Parcel
import android.os.Parcelable


@Parcelize
data class User(val id:Int
                ,val fName:String,
                val lName:String,
                val score:Int):Parcelable