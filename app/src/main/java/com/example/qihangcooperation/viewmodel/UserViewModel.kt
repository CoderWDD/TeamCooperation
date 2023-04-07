package com.example.qihangcooperation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qihangcooperation.constants.HttpStatusConstants
import com.example.qihangcooperation.pojo.UserDTO
import com.example.qihangcooperation.repository.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {

    private val userRepository: UserRepository = UserRepository()
    private val _loginRes: MutableStateFlow<LoginRes> = MutableStateFlow(LoginRes.NotReady)
    val loginRes: StateFlow<LoginRes> = _loginRes

    private val _registerRes: MutableStateFlow<RegisterRes> = MutableStateFlow(RegisterRes.NotReady)
    val registerRes: StateFlow<RegisterRes> = _registerRes

    fun login(username: String, password: String) {
        viewModelScope.launch {
            userRepository.login(username, password).collect{
                when (it.status) {
                    HttpStatusConstants.SUCCESS.code -> _loginRes.value = LoginRes.Success(it.data)
                    else -> _loginRes.value = LoginRes.Error(it.message)
                }
            }
        }
    }

    fun register(username: String, password: String) {
        viewModelScope.launch {
            userRepository.register(username = username, password = password).collect{
                when (it.status) {
                    HttpStatusConstants.SUCCESS.code -> _registerRes.value = RegisterRes.Success(UserDTO(username, password))
                    else -> _registerRes.value = RegisterRes.Error(it.message)
                }
            }
        }
    }
}

sealed class LoginRes{
    object NotReady: LoginRes()
    data class Success(val token: Map<String, String>): LoginRes()
    data class Error(val error: String): LoginRes()
}

sealed class RegisterRes{
    object NotReady: RegisterRes()
    data class Success(val user: UserDTO): RegisterRes()
    data class Error(val error: String): RegisterRes()
}