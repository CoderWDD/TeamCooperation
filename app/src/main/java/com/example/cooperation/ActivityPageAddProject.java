package com.example.cooperation;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cooperation.databinding.ActivityPageAddProjectBinding;
import com.example.cooperation.databing.PageAddProjectDataBinding;

public class ActivityPageAddProject extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPageAddProjectBinding activityPageAddProjectBinding = DataBindingUtil.setContentView(this, R.layout.activity_page_add_project);
        activityPageAddProjectBinding.setEventHandler(new PageAddProjectDataBinding(this));

        WindowManager windowManager = getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        Point size = new Point();
        defaultDisplay.getSize(size);
        layoutParams.height = (int) (size.y * 0.32);
        layoutParams.width = (int) (size.x * 0.9);
        getWindow().setAttributes(layoutParams);
    }
}