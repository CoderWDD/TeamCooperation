package com.example.cooperation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.cooperation.databinding.ActivityLoginPageBinding;
import com.example.cooperation.databing.PageLoginDataBinding;

public class LoginPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除系统状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_login_page);

        ActivityLoginPageBinding activityLoginPageBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_page);
        activityLoginPageBinding.setLoginPageEventHandler(new PageLoginDataBinding(this));

    }



}