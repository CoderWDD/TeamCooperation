package com.example.cooperation;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cooperation.databinding.ActivityFloatingActionButtonChooseBinding;
import com.example.cooperation.databing.FragmentFloatingActionButtonDataBinding;

public class ActivityFloatingActionButtonChoose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除系统状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActivityFloatingActionButtonChooseBinding actionButtonChooseBinding = DataBindingUtil.setContentView(this, R.layout.activity_floating_action_button_choose);

        actionButtonChooseBinding.setFloatingActionButtonHandler(new FragmentFloatingActionButtonDataBinding(this));

    }
}