package com.example.cooperation;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cooperation.databinding.ActivityPageChangeUserPasswordBinding;
import com.example.cooperation.databing.ActivityPageChangePasswordDataBinding;
import com.example.cooperation.utils.ScreenAdapterUtil;

public class ActivityPageChangeUserPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityPageChangeUserPasswordBinding activityPageChangeUserPasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_page_change_user_password);

        activityPageChangeUserPasswordBinding.setEventHandler(new ActivityPageChangePasswordDataBinding(this));

        WindowManager windowManager = getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        Point size = new Point();
        defaultDisplay.getSize(size);
        layoutParams.height = ScreenAdapterUtil.dp2px(this,240);
        layoutParams.width = (int) (size.x * 0.9);
        getWindow().setAttributes(layoutParams);
    }
}