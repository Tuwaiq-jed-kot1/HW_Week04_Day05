package com.sumaya.myapplication.register

import androidx.lifecycle.ViewModel
import com.sumaya.myapplication.data.Repo

class MainViewModel : ViewModel() {
   private var repo =Repo()

    fun fillInfo(name:String,bDay:String, gender:String, phone:String){
     repo.fillInfo(name, bDay, gender, phone)
    }
    fun getInfo() = repo.getInfo()
}