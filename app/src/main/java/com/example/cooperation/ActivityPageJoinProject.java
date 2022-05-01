package com.example.cooperation;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cooperation.databinding.ActivityPageJoinProjectBinding;
import com.example.cooperation.databing.ActivityPageJoinProjectDataBinding;

public class ActivityPageJoinProject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPageJoinProjectBinding activityPageJoinProjectBinding = DataBindingUtil.setContentView(this,R.layout.activity_page_join_project);
        activityPageJoinProjectBinding.setEventHandler(new ActivityPageJoinProjectDataBinding(this));

        WindowManager windowManager = getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        Point size = new Point();
        defaultDisplay.getSize(size);
        layoutParams.height = (int) (size.y * 0.3);
        layoutParams.width = (int) (size.x * 0.9);
        getWindow().setAttributes(layoutParams);
    }
}