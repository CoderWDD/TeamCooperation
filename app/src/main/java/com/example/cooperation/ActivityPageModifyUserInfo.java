package com.example.cooperation;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cooperation.api.MyRetrofit;
import com.example.cooperation.databinding.ActivityPageModifyUserInfoBinding;
import com.example.cooperation.databing.ActivityPageUserInfoDataBinding;

public class ActivityPageModifyUserInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除系统状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        ActivityPageModifyUserInfoBinding activityPageModifyUserInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_page_modify_user_info);

        activityPageModifyUserInfoBinding.setEventHandler(new ActivityPageUserInfoDataBinding(this,MyRetrofit.getUser()));

        activityPageModifyUserInfoBinding.setUserInfo(MyRetrofit.getUser());

    }
}