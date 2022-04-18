package com.example.cooperation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.cooperation.databinding.ActivitySignUpPageBinding;
import com.example.cooperation.databing.PageSignUpDataBinding;

public class SignUpPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除系统状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up_page);
        ActivitySignUpPageBinding activitySignUpPageBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_page);
        activitySignUpPageBinding.setSignUpPageEventHandler(new PageSignUpDataBinding(this));

    }
}