package com.example.qihangcooperation.networks

import com.example.qihangcooperation.pojo.*
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {
    @POST("/authenticate")
    suspend fun authenticate(@Body user: UserDTO): Map<String, String>

    @POST("/register")
    suspend fun register(@Body userDTO: UserDTO)

    @POST("/api/project/create")
    suspend fun createProject(@Body project: Project): Map<String, Long>

    @PUT("/api/project/update/{id}")
    suspend fun updateProject(@Body project: Project, @Path("id") id: Long)

    @DELETE("/api/project/delete/{id}")
    suspend fun deleteProject(@Path("id") id: Long)

    @DELETE("/api/project/delete/multi")
    suspend fun deleteProjects(@Body projectSet: ProjectSet)

    @GET("/api/project/get/{id}")
    suspend fun getProject(@Path("id") id: Long): Project

    @GET("/api/project/get/all")
    suspend fun getAllProjects(@Query("username") username: String): List<Project>

    @GET("/api/project/get/manage")
    suspend fun getManageProjects(@Query("username") username: String): List<Project>

    @POST("/api/project/invite/{id}")
    suspend fun inviteUser(@Path("id") id: Long, @Query("username") username: String)

    @POST("/api/project/invite/code")
    suspend fun inviteByCode(@Query("inviteCode") inviteCode: String)

    @DELETE("/api/project/invite/{id}")
    suspend fun deleteInvite(@Path("id") id: Long, @Query("username") username: String)

    @GET("/api/project/get/users/{id}")
    suspend fun getProjectUsers(@Path("id") id: Long): Set<UserDTO>

    @POST("/api/task/create")
    suspend fun createTask(@Body task: Task, @Query("projectId") projectId: Long): Map<String, Long>

    @PUT("/api/task/update/{id}")
    suspend fun updateTask(@Body task: Task, @Path("id") id: Long, @Query("projectId") projectId: Long)

    @GET("/api/task/get/{id}")
    suspend fun getTask(@Path("id") id: Long): Task

    @GET("/api/task/get/all")
    suspend fun getTaskList(@Query("projectId") projectId: Long): List<Task>

    @DELETE("/api/task/delete/{id}")
    suspend fun deleteTask(@Path("id") id: Long)

    @DELETE("/api/task/delete/multi")
    suspend fun deleteMultiTasks(@Body taskSet: TaskSet)

    @GET("/api/task/get/users/{id}")
    suspend fun getAllInvitedUser(@Path("id") id: Long): List<User>

    @POST("/api/task/add/user/{id}")
    suspend fun addUserToTask(@Path("id") id: Long, @Query("userId") userId: Long)

    @GET("/api/user/current_user")
    suspend fun currentUser(): User

    @GET("/api/user/get/all")
    suspend fun getAllUserName(): List<String>

}