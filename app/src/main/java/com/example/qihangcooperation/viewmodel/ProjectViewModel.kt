package com.example.qihangcooperation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qihangcooperation.constants.HttpStatusConstants
import com.example.qihangcooperation.pojo.Project
import com.example.qihangcooperation.pojo.ProjectSet
import com.example.qihangcooperation.pojo.Task
import com.example.qihangcooperation.pojo.TaskSet
import com.example.qihangcooperation.repository.ProjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class ProjectViewModel : ViewModel() {
    private val projectRepository = ProjectRepository(viewModelScope)

    // Create Project
    fun createProject(project: Project) = flow {
        emit(projectRepository.createProject(project = project))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Get Projects
    fun getProjects(username: String) = flow {
        emit(projectRepository.getAllProjects(username))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Get Project
    fun getProject(projectId: Long) = flow {
        emit(projectRepository.getProject(projectId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Update Project
    fun updateProject(project: Project, projectId: Long) = flow {
        emit(projectRepository.updateProject(project, projectId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Delete Project
    fun deleteProject(projectId: Long) = flow {
        emit(projectRepository.deleteProject(projectId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Delete Projects
    fun deleteProjects(projectSet: ProjectSet) = flow {
        emit(projectRepository.deleteProjects(projectSet))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Get Manager Projects
    fun getManageProjects(username: String) = flow {
        emit(projectRepository.getManageProjects(username))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Invite Member
    fun inviteMember(projectId: Long, username: String) = flow {
        emit(projectRepository.inviteUser(projectId, username))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Invite By Code
    fun inviteByCode(code: String) = flow {
        emit(projectRepository.inviteByCode(code))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Delete Member
    fun deleteMember(projectId: Long, username: String) = flow {
        emit(projectRepository.deleteMember(projectId, username))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Get Possible Members
    fun getPossibleMembers(projectId: Long) = flow {
        emit(projectRepository.getPossibleMembers(projectId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Create Task
    fun createTask(projectId: Long, task: Task) = flow {
        emit(projectRepository.createTask(projectId, task))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Get Tasks
    fun getTasks(projectId: Long) = flow {
        emit(projectRepository.getAllTasks(projectId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Get Task
    fun getTask(taskId: Long) = flow {
        emit(projectRepository.getTask(taskId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Update Task
    fun updateTask(projectId: Long, taskId: Long, task: Task) = flow {
        emit(projectRepository.updateTask(projectId = projectId, task = task, id = taskId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Delete Task
    fun deleteTask(taskId: Long) = flow {
        emit(projectRepository.deleteTask(taskId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Delete Tasks
    fun deleteTasks(taskSet: TaskSet) = flow {
        emit(projectRepository.deleteTasks(taskSet))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Get All Invited Users
    fun getAllInvitedUsers(projectId: Long) = flow {
        emit(projectRepository.getAllInvitedUsers(projectId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Add User To Task
    fun addUserToTask(taskId: Long, userId: Long) = flow {
        emit(projectRepository.addUserToTask(taskId, userId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Get Current User Is Manager
    fun getCurrentUserIsManager() = flow {
        emit(projectRepository.currentUserIsManager())
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Get All User Name
    fun getAllUserName() = flow {
        emit(projectRepository.getAllUserName())
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    sealed class ProjectAndTaskState<out T> {
        data class Loading(val msg: String): ProjectAndTaskState<Nothing>()
        data class Success<T>(val res: T) : ProjectAndTaskState<T>()
        data class Failed(val reason: String) : ProjectAndTaskState<Nothing>()
    }
}