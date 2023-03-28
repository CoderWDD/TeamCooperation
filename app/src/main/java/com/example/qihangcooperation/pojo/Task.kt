package com.example.qihangcooperation.pojo

import java.time.LocalDateTime

data class Task(
    val taskId: Long,
    val taskName: String,
    val status: String,
    val startTime: LocalDateTime? = null,
    val updateTime: LocalDateTime? = null,
    val endTime: LocalDateTime,
    val description: String,
    val taskOwner: User
)
