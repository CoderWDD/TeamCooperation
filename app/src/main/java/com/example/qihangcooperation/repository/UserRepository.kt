package com.example.qihangcooperation.repository

import androidx.lifecycle.viewModelScope
import com.example.qihangcooperation.networks.RetrofitClient
import com.example.qihangcooperation.pojo.UserDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserRepository(private val externalScope: CoroutineScope) {
    fun login(username: String, password: String) = flow{
        val userDTO = UserDTO(username = username, password = password)
        emit(RetrofitClient.retrofitService.authenticate(userDTO))
    }.flowOn(Dispatchers.IO)

    fun register(username: String, password: String) = flow {
        val userDTO = UserDTO(username = username, password = password)
        emit(RetrofitClient.retrofitService.register(userDTO))
    }.flowOn(Dispatchers.IO)
}