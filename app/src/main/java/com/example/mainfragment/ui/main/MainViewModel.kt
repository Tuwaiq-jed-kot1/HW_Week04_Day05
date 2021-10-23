package com.example.mainfragment.ui.main

import androidx.lifecycle.ViewModel
import com.example.mainfragment.data.Repo
import com.example.mainfragment.data.model.User

class MainViewModel : ViewModel() {
    private val repo = Repo()

    fun getAllUsers():List<User> = repo.fillRV()

}