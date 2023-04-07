package com.example.qihangcooperation.repository

import com.example.qihangcooperation.constants.HttpStatusConstants
import com.example.qihangcooperation.networks.RetrofitClient
import com.example.qihangcooperation.pojo.UserDTO
import com.example.qihangcooperation.response.ResponseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class UserRepository() {
    fun login(username: String, password: String) = flow{
        val userDTO = UserDTO(username = username, password = password)
        emit(RetrofitClient.retrofitService.authenticate(userDTO))
    }.catch {
        val responseData: ResponseData<Map<String, String>> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = mapOf())
        emit(responseData)
    }.flowOn(Dispatchers.IO)

    fun register(username: String, password: String) = flow {
        val userDTO = UserDTO(username = username, password = password)
        emit(RetrofitClient.retrofitService.register(userDTO))
    }.catch {
        val responseData: ResponseData<Any> = ResponseData(status = HttpStatusConstants.SOME_ERROR.code, message = "Unknown Error", data = Any())
        emit(responseData)
    }.flowOn(Dispatchers.IO)
}