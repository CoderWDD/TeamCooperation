package com.example.qihangcooperation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qihangcooperation.constants.HttpStatusConstants
import com.example.qihangcooperation.constants.ProjectAndTaskStatus
import com.example.qihangcooperation.pojo.*
import com.example.qihangcooperation.repository.ProjectRepository
import com.example.qihangcooperation.response.ResponseData
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
    }.catch {
        val responseData: ResponseData<Map<String, Long>> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = mapOf())
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<List<Project>> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = listOf())
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<Project> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = Project(projectName = "Error Project", projectDescription = "Error Project"))
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<Unit> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = Unit)
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<Any> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = Any())
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<Any> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = Any())
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<List<Project>> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = listOf())
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<Any> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = Any())
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<Any> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = Any())
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<Any> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = Any())
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<Set<UserDTO>> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = setOf())
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<Map<String, Long>> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = mapOf())
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<List<Task>> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = listOf())
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<Task> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = Any() as Task)
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<Any> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = Any())
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<Any> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = Any())
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<Any> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = Any())
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<List<User>> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = listOf())
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<Any> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = Any())
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<User> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = User(username = "Error User"))
        emit(responseData)
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
    }.catch {
        val responseData: ResponseData<List<String>> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = listOf())
        emit(responseData)
    }.map {
        when (it.status){
            HttpStatusConstants.SUCCESS.code -> ProjectAndTaskState.Success(it.data)
            else -> ProjectAndTaskState.Failed(it.message)
        }
    }.catch {
        emit(ProjectAndTaskState.Failed(it.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    // Get All Task By User
    fun getAllTaskByUser() = flow {
        emit(projectRepository.getAllTaskByUser())
    }.catch {
        val responseData: ResponseData<List<Task>> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = listOf())
        emit(responseData)
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