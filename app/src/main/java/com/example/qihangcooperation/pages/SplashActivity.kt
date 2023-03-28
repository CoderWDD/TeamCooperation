package com.example.qihangcooperation.pages

import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.qihangcooperation.application.CooperationApplication
import com.example.qihangcooperation.base.BaseActivity
import com.example.qihangcooperation.constants.SharedPreferencesConstant
import com.example.qihangcooperation.databinding.ActivitySplashBinding
import com.example.qihangcooperation.pojo.User
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun onCreate() {
        initData()
        initClickListener()
    }

    private fun initData(){
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                getDataFromSP()
            }
        }
    }

    private fun initClickListener(){
        viewBinding.pageSplashSkip.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }

    private fun getDataFromSP(){
        val sharedPreferences = getSharedPreferences(SharedPreferencesConstant.SP_NAME, MODE_PRIVATE)
        val userId = sharedPreferences.getLong(SharedPreferencesConstant.USER_ID, 0)
        val firstUse = sharedPreferences.getBoolean(SharedPreferencesConstant.FIRST_USE, false)
        val username = sharedPreferences.getString(SharedPreferencesConstant.USERNAME, "")
        val password = sharedPreferences.getString(SharedPreferencesConstant.PASSWORD, "")
        val nickname = sharedPreferences.getString(SharedPreferencesConstant.NICKNAME, "")
        val email = sharedPreferences.getString(SharedPreferencesConstant.EMAIL, "")
        val user = User(userId = userId, username = username!!, password = password!!, nickname = nickname!!, email = email!!)
        // 将 user 保存到 application 中
        CooperationApplication.setUser(user)
    }
}