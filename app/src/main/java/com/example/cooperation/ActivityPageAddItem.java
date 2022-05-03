package com.example.cooperation;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cooperation.databinding.ActivityPageAddItemBinding;
import com.example.cooperation.databing.PageAddItemDataBinding;
import com.example.cooperation.model.ItemAdd;
import com.example.cooperation.model.Project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ActivityPageAddItem extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除系统状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        ActivityPageAddItemBinding activityPageAddItemBinding = DataBindingUtil.setContentView(this, R.layout.activity_page_add_item);

        activityPageAddItemBinding.setEventHandler(new PageAddItemDataBinding(this));

        Bundle bundle = getIntent().getExtras();
        Project project = (Project) bundle.getSerializable("project");

        ItemAdd item = new ItemAdd();
        item.setProjectId(project.getProjectId());
        item.setAuthor(project.getAuthor());
        item.setProjectName(project.getProjectName());
        activityPageAddItemBinding.setItem(item);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        item.setItemTime(simpleDateFormat.format(new Date()));

        // 配置Status Spinner
        Spinner spinnerStatus = findViewById(R.id.spinner_status);
        String[] stringArray = getResources().getStringArray(R.array.status);
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<>(this, R.layout.status_spinner_text, stringArray);
        adapterStatus.setDropDownViewResource(R.layout.status_spinner_text);
        spinnerStatus.setAdapter(adapterStatus);

        // 配置Executor Spinner
        Spinner spinnerCooperators = findViewById(R.id.spinner_executor);
        List<String> cooperatorsList = project.getCooperatorsList();

        // 避免NPE
        if (cooperatorsList == null){
            cooperatorsList = new LinkedList<>();
        }

        // 默认最少有自己一个执行者
        cooperatorsList.add(project.getAuthor());
        ArrayAdapter<String> adapterCooperators = new ArrayAdapter<>(this, R.layout.status_spinner_text, cooperatorsList);
        adapterCooperators.setDropDownViewResource(R.layout.status_spinner_text);
        spinnerCooperators.setAdapter(adapterCooperators);

    }
}