package com.example.qihangcooperation.repository

import androidx.lifecycle.viewModelScope
import com.example.qihangcooperation.networks.RetrofitClient
import com.example.qihangcooperation.pojo.Project
import com.example.qihangcooperation.pojo.ProjectSet
import com.example.qihangcooperation.pojo.Task
import com.example.qihangcooperation.pojo.TaskSet
import com.example.qihangcooperation.response.ResponseData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProjectRepository(private val externalScope: CoroutineScope) {
    suspend fun createProject(project: Project): ResponseData<Map<String, Long>> = RetrofitClient.retrofitService.createProject(project = project)

    suspend fun updateProject(project: Project, id: Long) = RetrofitClient.retrofitService.updateProject(project = project, id = id)

    suspend fun deleteProject(id: Long) = RetrofitClient.retrofitService.deleteProject(id = id)

    suspend fun deleteProjects(projectSet: ProjectSet) = RetrofitClient.retrofitService.deleteProjects(projectSet = projectSet)

    suspend fun getProject(id: Long) = RetrofitClient.retrofitService.getProject(id = id)

    suspend fun getAllProjects(username: String) = RetrofitClient.retrofitService.getAllProjects(username = username)

    suspend fun getManageProjects(username: String) = RetrofitClient.retrofitService.getManageProjects(username = username)

    suspend fun inviteUser(projectId: Long, username: String) = RetrofitClient.retrofitService.inviteUser(id = projectId, username = username)

    suspend fun inviteByCode(code: String) = RetrofitClient.retrofitService.inviteByCode(inviteCode = code)

    suspend fun deleteMember(projectId: Long, username: String) = RetrofitClient.retrofitService.deleteInvite(id = projectId, username = username)

    suspend fun getPossibleMembers(projectId: Long) = RetrofitClient.retrofitService.getProjectUsers(id = projectId)

    suspend fun createTask(projectId: Long, task: Task) = RetrofitClient.retrofitService.createTask(projectId = projectId, task = task)

    suspend fun updateTask(projectId: Long, task: Task, id: Long) = RetrofitClient.retrofitService.updateTask(projectId = projectId, task = task, id = id)

    suspend fun deleteTask(id: Long) = RetrofitClient.retrofitService.deleteTask(id = id)

    suspend fun deleteTasks(taskSet: TaskSet) = RetrofitClient.retrofitService.deleteMultiTasks(taskSet = taskSet)

    suspend fun getTask(id: Long) = RetrofitClient.retrofitService.getTask(id = id)

    suspend fun getAllTasks(projectId: Long) = RetrofitClient.retrofitService.getTaskList(projectId = projectId)

    suspend fun getAllInvitedUsers(id: Long) = RetrofitClient.retrofitService.getAllInvitedUser(id = id)

    suspend fun addUserToTask(taskId: Long, userId: Long) = RetrofitClient.retrofitService.addUserToTask(id = taskId, userId = userId)

    suspend fun currentUserIsManager() = RetrofitClient.retrofitService.currentUser()

    suspend fun getAllUserName() = RetrofitClient.retrofitService.getAllUserName()
}