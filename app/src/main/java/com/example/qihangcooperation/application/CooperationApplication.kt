package com.example.qihangcooperation.application

import android.app.Application
import com.example.qihangcooperation.pojo.User

class CooperationApplication : Application(){


    companion object{
        private lateinit var user: User

        fun setUser(user: User){
            this.user = user
        }

        fun getUser() = user
    }



}