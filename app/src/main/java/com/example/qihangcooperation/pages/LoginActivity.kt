package com.example.qihangcooperation.pages

import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.qihangcooperation.MainActivity
import com.example.qihangcooperation.application.CooperationApplication
import com.example.qihangcooperation.base.BaseActivity
import com.example.qihangcooperation.constants.ActivityResultConstants
import com.example.qihangcooperation.databinding.ActivityLoginBinding
import com.example.qihangcooperation.util.ResponseHandler
import com.example.qihangcooperation.viewmodel.LoginRes
import com.example.qihangcooperation.viewmodel.UserViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == ActivityResultConstants.REGISTER_SUCCESS.code) {
            // get the username and password from the signup activity
            val data: Intent? = result.data
            val username = data?.getStringExtra("username")
            val password = data?.getStringExtra("password")
            // set the username and password to the login activity
            viewBinding.loginEtUsername.setText(username)
            viewBinding.loginEtPassword.setText(password)
        }
    }

    private lateinit var viewModel: UserViewModel
    override fun onCreate() {
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        initClickListener()
        initViewModelCallback()
    }

    private fun initClickListener(){
        viewBinding.buttonLogin.setOnClickListener {
            val username = viewBinding.loginEtUsername.text.toString()
            val password = viewBinding.loginEtPassword.text.toString()
            viewModel.login(username, password)
        }

        viewBinding.buttonSignUp.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startForResult.launch(intent)
        }
    }

    private fun initViewModelCallback(){
        lifecycleScope.launchWhenCreated {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.loginRes.collect{
                    when (it) {
                        is LoginRes.Error -> ResponseHandler.handleError(it.error, this@LoginActivity)
                        is LoginRes.Success -> {
                            // save token to application store
                            CooperationApplication.setGlobalToken(it.token)
                            // switch to the main activity
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(intent)
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}