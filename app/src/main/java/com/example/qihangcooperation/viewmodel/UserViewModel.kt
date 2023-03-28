package com.example.qihangcooperation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qihangcooperation.networks.RetrofitClient
import com.example.qihangcooperation.pojo.UserDTO
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {

    fun login(username: String, password: String) = flow{
        val userDTO = UserDTO(username = username, password = password)
        emit(RetrofitClient.retrofitService.authenticate(userDTO))
    }.flowOn(viewModelScope.coroutineContext)
        .catch { e -> e.printStackTrace()}

    fun register(username: String, password: String) {
        viewModelScope.launch {
            val userDTO = UserDTO(username = username, password = password)
            RetrofitClient.retrofitService.register(userDTO)
        }
    }
}