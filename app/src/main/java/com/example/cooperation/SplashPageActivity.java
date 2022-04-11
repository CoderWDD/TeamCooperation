package com.example.cooperation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.cooperation.databinding.ActivitySplashPageBinding;
import com.example.cooperation.databing.PageSplashDataBinding;

public class SplashPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 去除系统状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash_page);
        ActivitySplashPageBinding activitySplashPageBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash_page);
        activitySplashPageBinding.setPageSplashEventHandler(new PageSplashDataBinding(this));

    }
}