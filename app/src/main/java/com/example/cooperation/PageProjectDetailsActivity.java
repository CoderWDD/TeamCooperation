package com.example.cooperation;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cooperation.databinding.ActivityPageProjectDetailsBinding;
import com.example.cooperation.databing.PageProjectDetailsDataBinding;

public class PageProjectDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除系统状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_page_project_details);
        ActivityPageProjectDetailsBinding activityPageProjectDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_page_project_details);

        activityPageProjectDetailsBinding.setEventHandler(new PageProjectDetailsDataBinding(this));

        // TODO 从Bundle里取出project的值，key：project_item
    }
}