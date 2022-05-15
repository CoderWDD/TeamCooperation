package com.example.cooperation;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cooperation.constant.ActivityRequestCodeConstant;
import com.example.cooperation.databinding.ActivityLoginPageBinding;
import com.example.cooperation.databing.PageLoginDataBinding;
import com.google.android.material.textfield.TextInputEditText;

public class LoginPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除系统状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        // 设置dataBing
        ActivityLoginPageBinding activityLoginPageBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_page);
        activityLoginPageBinding.setLoginPageEventHandler(new PageLoginDataBinding(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ActivityRequestCodeConstant.SIGNUP){
            assert data != null;
            String username = data.getStringExtra("username");
            String password = data.getStringExtra("password");
            TextInputEditText usernameText = findViewById(R.id.login_et_username);
            TextInputEditText passwordText = findViewById(R.id.login_et_password);
            usernameText.setText(username);
            passwordText.setText(password);
        }
    }


}