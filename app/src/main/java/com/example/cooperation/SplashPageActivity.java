package com.example.cooperation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cooperation.constant.SharedPreferencesConstant;
import com.example.cooperation.databinding.ActivitySplashPageBinding;
import com.example.cooperation.databing.PageSplashDataBinding;

public class SplashPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 从本地中判断是否第一次打开此app

        SharedPreferences sharedPreferences = getSharedPreferences(SharedPreferencesConstant.SP_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sharedPreferences.edit();

        boolean firstUse = sharedPreferences.getBoolean(SharedPreferencesConstant.FIRST_USE, true);

        if (!firstUse) {
            Intent intent = new Intent(this, LoginPageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            this.startActivity(intent);
            return;
        }

        // 去除系统状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash_page);
        ActivitySplashPageBinding activitySplashPageBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash_page);
        activitySplashPageBinding.setPageSplashEventHandler(new PageSplashDataBinding(this));


    }
}