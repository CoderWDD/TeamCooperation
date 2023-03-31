package com.example.qihangcooperation.response

data class ResponseData<T>(
    val status: Int,
    val message: String,
    val data: T
)