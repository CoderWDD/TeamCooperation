package com.example.cooperation;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cooperation.databinding.ActivityPageAddProjectBinding;
import com.example.cooperation.databing.PageAddProjectDataBinding;
import com.example.cooperation.utils.ScreenAdapterUtil;

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
        layoutParams.height = ScreenAdapterUtil.dp2px(this,310);
        layoutParams.width = (int) (size.x * 0.9);
        getWindow().setAttributes(layoutParams);

        // 配置Priority Spinner
        Spinner spinnerItemPriority = findViewById(R.id.spinner_project_create_priority);
        String[] priorityArray = getResources().getStringArray(R.array.priority);
        ArrayAdapter<String> adapterPriority = new ArrayAdapter<>(this, R.layout.status_spinner_text, priorityArray);
        adapterPriority.setDropDownViewResource(R.layout.status_spinner_text);
        spinnerItemPriority.setAdapter(adapterPriority);

    }
}