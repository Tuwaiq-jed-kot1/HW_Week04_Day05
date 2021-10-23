package com.sumaya.myapplication.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserInfo (var name:String,var bDay:String,var gender:String,var phone:String):Parcelable