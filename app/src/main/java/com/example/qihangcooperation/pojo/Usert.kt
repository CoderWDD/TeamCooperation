package com.example.qihangcooperation.pojo

data class User(
    val userId: Long = 0,
    val username: String,
    val password: String = "",
    val email: String = "",
    val nickname: String = ""
)
