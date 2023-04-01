package com.example.qihangcooperation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qihangcooperation.constants.HttpStatusConstants
import com.example.qihangcooperation.pojo.Project
import com.example.qihangcooperation.pojo.ProjectSet
import com.example.qihangcooperation.pojo.Task
import com.example.qihangcooperation.pojo.TaskSet
import com.example.qihangcooperation.repository.ProjectRepository
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
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Get Projects
    fun getProjects(username: String) = flow {
        emit(projectRepository.getAllProjects(username))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Get Project
    fun getProject(projectId: Long) = flow {
        emit(projectRepository.getProject(projectId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Update Project
    fun updateProject(project: Project, projectId: Long) = flow {
        emit(projectRepository.updateProject(project, projectId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Delete Project
    fun deleteProject(projectId: Long) = flow {
        emit(projectRepository.deleteProject(projectId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Delete Projects
    fun deleteProjects(projectSet: ProjectSet) = flow {
        emit(projectRepository.deleteProjects(projectSet))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Get Manager Projects
    fun getManageProjects(username: String) = flow {
        emit(projectRepository.getManageProjects(username))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Invite Member
    fun inviteMember(projectId: Long, username: String) = flow {
        emit(projectRepository.inviteUser(projectId, username))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Invite By Code
    fun inviteByCode(code: String) = flow {
        emit(projectRepository.inviteByCode(code))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Delete Member
    fun deleteMember(projectId: Long, username: String) = flow {
        emit(projectRepository.deleteMember(projectId, username))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Get Possible Members
    fun getPossibleMembers(projectId: Long) = flow {
        emit(projectRepository.getPossibleMembers(projectId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Create Task
    fun createTask(projectId: Long, task: Task) = flow {
        emit(projectRepository.createTask(projectId, task))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Get Tasks
    fun getTasks(projectId: Long) = flow {
        emit(projectRepository.getAllTasks(projectId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Get Task
    fun getTask(taskId: Long) = flow {
        emit(projectRepository.getTask(taskId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Update Task
    fun updateTask(projectId: Long, taskId: Long, task: Task) = flow {
        emit(projectRepository.updateTask(projectId = projectId, task = task, id = taskId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Delete Task
    fun deleteTask(taskId: Long) = flow {
        emit(projectRepository.deleteTask(taskId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Delete Tasks
    fun deleteTasks(taskSet: TaskSet) = flow {
        emit(projectRepository.deleteTasks(taskSet))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Get All Invited Users
    fun getAllInvitedUsers(projectId: Long) = flow {
        emit(projectRepository.getAllInvitedUsers(projectId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Add User To Task
    fun addUserToTask(taskId: Long, userId: Long) = flow {
        emit(projectRepository.addUserToTask(taskId, userId))
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Get Current User Is Manager
    fun getCurrentUserIsManager() = flow {
        emit(projectRepository.currentUserIsManager())
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)

    // Get All User Name
    fun getAllUserName() = flow {
        emit(projectRepository.getAllUserName())
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectState.Success(it.data)
            else -> ProjectState.Failed(it.message)
        }
    }.catch {
        emit(ProjectState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(viewModelScope.coroutineContext)


    sealed class ProjectState {
        data class Loading(val msg: String): ProjectState()
        data class Success(val res: Any) : ProjectState()
        data class Failed(val reason: String) : ProjectState()
    }
}