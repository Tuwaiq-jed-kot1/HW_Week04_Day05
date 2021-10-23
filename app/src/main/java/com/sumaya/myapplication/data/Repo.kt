package com.sumaya.myapplication.data

import com.sumaya.myapplication.data.model.UserInfo

class Repo {


    private var user :UserInfo?=null

    fun fillInfo(name: String, bDay: String, gender: String, phone: String): UserInfo {
       user = UserInfo(name, bDay, gender, phone)
        return user as UserInfo
    }

    fun getInfo(): UserInfo? = user


}