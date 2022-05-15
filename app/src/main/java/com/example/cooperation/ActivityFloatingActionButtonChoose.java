package com.example.cooperation;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cooperation.databinding.ActivityFloatingActionButtonChooseBinding;
import com.example.cooperation.databing.ActivityFloatingActionButtonDataBinding;
import com.example.cooperation.utils.ScreenAdapterUtil;

public class ActivityFloatingActionButtonChoose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(this,getTitle(),Toast.LENGTH_SHORT).show();

        ActivityFloatingActionButtonChooseBinding actionButtonChooseBinding = DataBindingUtil.setContentView(this, R.layout.activity_floating_action_button_choose);

        actionButtonChooseBinding.setFloatingActionButtonHandler(new ActivityFloatingActionButtonDataBinding(this));

        WindowManager windowManager = getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        Point size = new Point();
        defaultDisplay.getSize(size);
        layoutParams.height = ScreenAdapterUtil.dp2px(this,210);
        layoutParams.width = (int) (size.x * 0.9);
        getWindow().setAttributes(layoutParams);
    }
}