package com.example.qihangcooperation.application

import android.app.Application
import com.example.qihangcooperation.pojo.User

class CooperationApplication : Application(){


    companion object{
        private lateinit var user: User
        private lateinit var token: Map<String, String>

        fun setUser(user: User){
            this.user = user
        }

        fun getUser() = user

        fun getGlobalToken() = token

        fun setGlobalToken(token: Map<String, String>) { this.token = token}
    }



}