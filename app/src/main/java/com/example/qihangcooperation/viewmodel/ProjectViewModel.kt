package com.example.qihangcooperation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qihangcooperation.networks.RetrofitClient
import com.example.qihangcooperation.pojo.Project
import com.example.qihangcooperation.pojo.ProjectSet
import com.example.qihangcooperation.pojo.Task
import com.example.qihangcooperation.pojo.TaskSet
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ProjectViewModel : ViewModel() {
    fun createProject(project: Project) = flow {
        emit(RetrofitClient.retrofitService.createProject(project = project))
    }.flowOn(viewModelScope.coroutineContext)
        .catch { e -> e.printStackTrace()}

    fun updateProject(project: Project, id: Long) = flow {
        emit(RetrofitClient.retrofitService.updateProject(project = project, id = id))
    }.flowOn(viewModelScope.coroutineContext)
        .catch { e -> e.printStackTrace()}

    fun deleteProject(id: Long) {
        viewModelScope.launch {
            RetrofitClient.retrofitService.deleteProject(id = id)
        }
    }

    fun deleteProjects(projectSet: ProjectSet) {
        viewModelScope.launch {
            RetrofitClient.retrofitService.deleteProjects(projectSet = projectSet)
        }
    }

    fun getProject(id: Long) = flow {
        emit(RetrofitClient.retrofitService.getProject(id = id))
    }.flowOn(viewModelScope.coroutineContext)
        .catch { e -> e.printStackTrace()}

    fun getAllProjects(username: String) = flow {
        emit(RetrofitClient.retrofitService.getAllProjects(username = username))
    }.flowOn(viewModelScope.coroutineContext)
        .catch { e -> e.printStackTrace()}

    fun getManageProjects(username: String) = flow {
        emit(RetrofitClient.retrofitService.getManageProjects(username = username))
    }.flowOn(viewModelScope.coroutineContext)
        .catch { e -> e.printStackTrace()}


    fun inviteUser(projectId: Long, username: String) {
        viewModelScope.launch {
            RetrofitClient.retrofitService.inviteUser(id = projectId, username = username)
        }
    }

    fun inviteByCode(code: String) {
        viewModelScope.launch {
            RetrofitClient.retrofitService.inviteByCode(inviteCode = code)
        }
    }

    fun deleteMember(projectId: Long, username: String) {
        viewModelScope.launch {
            RetrofitClient.retrofitService.deleteInvite(id = projectId, username = username)
        }
    }

    fun getPossibleMembers(projectId: Long) = flow {
        emit(RetrofitClient.retrofitService.getProjectUsers(id = projectId))
    }.flowOn(viewModelScope.coroutineContext)
        .catch { e -> e.printStackTrace()}

    fun createTask(projectId: Long, task: Task) = flow {
        emit(RetrofitClient.retrofitService.createTask(projectId = projectId, task = task))
    }.flowOn(viewModelScope.coroutineContext)

    fun updateTask(projectId: Long, task: Task, id: Long) = flow {
        emit(RetrofitClient.retrofitService.updateTask(projectId = projectId, task = task, id = id))
    }.flowOn(viewModelScope.coroutineContext)

    fun deleteTask(id: Long) {
        viewModelScope.launch {
            RetrofitClient.retrofitService.deleteTask(id = id)
        }
    }

    fun deleteTasks(taskSet: TaskSet) {
        viewModelScope.launch {
            RetrofitClient.retrofitService.deleteMultiTasks(taskSet = taskSet)
        }
    }

    fun getTask(id: Long) = flow {
        emit(RetrofitClient.retrofitService.getTask(id = id))
    }.flowOn(viewModelScope.coroutineContext)

    fun getAllTasks(projectId: Long) = flow {
        emit(RetrofitClient.retrofitService.getTaskList(projectId = projectId))
    }.flowOn(viewModelScope.coroutineContext)

    fun getAllInvitedUsers(id: Long) = flow {
        emit(RetrofitClient.retrofitService.getAllInvitedUser(id = id))
    }.flowOn(viewModelScope.coroutineContext)

    fun addUserToTask(taskId: Long, userId: Long) {
        viewModelScope.launch {
            RetrofitClient.retrofitService.addUserToTask(id = taskId, userId = userId)
        }
    }

    fun currentUserIsManager() = flow {
        emit(RetrofitClient.retrofitService.currentUser())
    }.flowOn(viewModelScope.coroutineContext)

    fun getAllUserName() = flow {
        emit(RetrofitClient.retrofitService.getAllUserName())
    }.flowOn(viewModelScope.coroutineContext)
}