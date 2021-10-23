package com.example.mainfragment.data

import com.example.mainfragment.data.model.User

class Repo {

    fun fillRV(): List<User> {
        val users = mutableListOf<User>()
        for (i in 1..100) {
            val user = User(i, "FN $i", "LN $i", i * 10)
            users += user

        }
        return users
    }

}