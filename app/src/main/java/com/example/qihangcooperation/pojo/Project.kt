package com.example.qihangcooperation.pojo

import com.example.qihangcooperation.constants.ProjectAndTaskStatus
import java.time.LocalDateTime

data class Project(
    val projectId: Long? = null,
    val projectName: String,
    val projectDescription: String,
    val projectStatus: String = ProjectAndTaskStatus.TODO.status,
    val projectManager: User? = null,
    val inviteCode: String? = null,
    val projectUpdatedDate: LocalDateTime? = null,
    val projectEndDate: LocalDateTime? = null,
    val user: Set<User>? = null
): java.io.Serializable
