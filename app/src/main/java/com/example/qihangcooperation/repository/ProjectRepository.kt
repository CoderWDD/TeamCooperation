package com.example.qihangcooperation.repository

import androidx.lifecycle.viewModelScope
import com.example.qihangcooperation.networks.RetrofitClient
import com.example.qihangcooperation.pojo.Project
import com.example.qihangcooperation.pojo.ProjectSet
import com.example.qihangcooperation.pojo.Task
import com.example.qihangcooperation.pojo.TaskSet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProjectRepository(private val externalScope: CoroutineScope) {
    fun createProject(project: Project) = flow {
        emit(RetrofitClient.retrofitService.createProject(project = project))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun updateProject(project: Project, id: Long) = flow {
        emit(RetrofitClient.retrofitService.updateProject(project = project, id = id))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun deleteProject(id: Long) = flow {
        emit(RetrofitClient.retrofitService.deleteProject(id = id))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun deleteProjects(projectSet: ProjectSet) = flow {
        emit(RetrofitClient.retrofitService.deleteProjects(projectSet = projectSet))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun getProject(id: Long) = flow {
        emit(RetrofitClient.retrofitService.getProject(id = id))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun getAllProjects(username: String) = flow {
        emit(RetrofitClient.retrofitService.getAllProjects(username = username))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun getManageProjects(username: String) = flow {
        emit(RetrofitClient.retrofitService.getManageProjects(username = username))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun inviteUser(projectId: Long, username: String) = flow {
        emit(RetrofitClient.retrofitService.inviteUser(id = projectId, username = username))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun inviteByCode(code: String) = flow {
        emit(RetrofitClient.retrofitService.inviteByCode(inviteCode = code))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun deleteMember(projectId: Long, username: String) = flow {
        emit(RetrofitClient.retrofitService.deleteInvite(id = projectId, username = username))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun getPossibleMembers(projectId: Long) = flow {
        emit(RetrofitClient.retrofitService.getProjectUsers(id = projectId))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun createTask(projectId: Long, task: Task) = flow {
        emit(RetrofitClient.retrofitService.createTask(projectId = projectId, task = task))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun updateTask(projectId: Long, task: Task, id: Long) = flow {
        emit(RetrofitClient.retrofitService.updateTask(projectId = projectId, task = task, id = id))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun deleteTask(id: Long) = flow {
        emit(RetrofitClient.retrofitService.deleteTask(id = id))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun deleteTasks(taskSet: TaskSet) = flow {
        emit(RetrofitClient.retrofitService.deleteMultiTasks(taskSet = taskSet))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun getTask(id: Long) = flow {
        emit(RetrofitClient.retrofitService.getTask(id = id))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun getAllTasks(projectId: Long) = flow {
        emit(RetrofitClient.retrofitService.getTaskList(projectId = projectId))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun getAllInvitedUsers(id: Long) = flow {
        emit(RetrofitClient.retrofitService.getAllInvitedUser(id = id))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun addUserToTask(taskId: Long, userId: Long) = flow {
        emit(RetrofitClient.retrofitService.addUserToTask(id = taskId, userId = userId))
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun currentUserIsManager() = flow {
        emit(RetrofitClient.retrofitService.currentUser())
    }.shareIn(externalScope, SharingStarted.Lazily, 1)

    fun getAllUserName() = flow {
        emit(RetrofitClient.retrofitService.getAllUserName())
    }.shareIn(externalScope, SharingStarted.Lazily, 1)
}