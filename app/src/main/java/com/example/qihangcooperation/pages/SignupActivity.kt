package com.example.qihangcooperation.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.qihangcooperation.MainActivity
import com.example.qihangcooperation.R
import com.example.qihangcooperation.application.CooperationApplication
import com.example.qihangcooperation.base.BaseActivity
import com.example.qihangcooperation.constants.ActivityResultConstants
import com.example.qihangcooperation.databinding.ActivitySignupBinding
import com.example.qihangcooperation.util.ResponseHandler
import com.example.qihangcooperation.viewmodel.LoginRes
import com.example.qihangcooperation.viewmodel.RegisterRes
import com.example.qihangcooperation.viewmodel.UserViewModel

class SignupActivity : BaseActivity<ActivitySignupBinding>(ActivitySignupBinding::inflate) {
    private lateinit var viewModel: UserViewModel
    private lateinit var username: String
    private lateinit var password: String
    override fun onCreate() {
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        initClickListener()
        initViewModelCallback()
    }

    private fun initClickListener(){
        viewBinding.buttonSignUp.setOnClickListener {
            username = viewBinding.signupEtUsername.text.toString()
            password = viewBinding.signupEtPassword.text.toString()
            viewModel.register(username, password)
        }
    }

    private fun initViewModelCallback(){
        lifecycleScope.launchWhenCreated {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.registerRes.collect{
                    when (it) {
                        is RegisterRes.Error -> ResponseHandler.handleError(it.error, this@SignupActivity)
                        is RegisterRes.Success -> {
                            val resIntent = Intent().apply {
                                putExtra("username", username)
                                putExtra("password", password)
                            }
                            setResult(ActivityResultConstants.REGISTER_SUCCESS.code, resIntent)
                            finish()
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}