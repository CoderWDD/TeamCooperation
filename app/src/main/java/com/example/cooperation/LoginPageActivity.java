package com.example.cooperation;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cooperation.databinding.ActivityLoginPageBinding;
import com.example.cooperation.databing.PageLoginDataBinding;

public class LoginPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除系统状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_login_page);

        // 设置dataBing
        ActivityLoginPageBinding activityLoginPageBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_page);
        activityLoginPageBinding.setLoginPageEventHandler(new PageLoginDataBinding(this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}