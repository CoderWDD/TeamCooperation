package com.example.qihangcooperation.pojo

data class Task(
    val taskId: Long? = null,
    val taskName: String,
    val status: String,
    val startTime: String? = null,
    val updateTime: String? = null,
    val endTime: String? = null,
    val description: String,
    val taskOwner: User
): java.io.Serializable
