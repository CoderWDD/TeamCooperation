package com.example.cooperation;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cooperation.databinding.ActivityPageTaskItemDetailsBinding;
import com.example.cooperation.databing.PageItemDetailsDataBinding;
import com.example.cooperation.model.ItemAdd;
import com.example.cooperation.utils.SpinnerPriorityUtil;

public class ActivityPageTaskItemDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除系统状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        ActivityPageTaskItemDetailsBinding activityPageTaskItemDetails = DataBindingUtil.setContentView(this, R.layout.activity_page_task_item_details);

        // 取出Bundle里的item，key：task_item

        Bundle bundle = getIntent().getExtras();

        ItemAdd task_item = (ItemAdd) bundle.getSerializable("task_item");

        activityPageTaskItemDetails.setItemDetails(task_item);

        activityPageTaskItemDetails.setEventHandler(new PageItemDetailsDataBinding(this));

        Spinner spinnerStatus = findViewById(R.id.spinner_item_status);
        String[] stringArray = getResources().getStringArray(R.array.status);
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<>(this, R.layout.status_spinner_text, stringArray);
        adapterStatus.setDropDownViewResource(R.layout.status_spinner_text);
        spinnerStatus.setAdapter(adapterStatus);
        // 设置默认的status为当前item的status
        int positionStatus = adapterStatus.getPosition(task_item.getStatus());
        spinnerStatus.setSelection(positionStatus);

        // 配置Priority Spinner
        Spinner spinnerItemPriority = findViewById(R.id.spinner_item_details_priority);
        String[] priorityArray = getResources().getStringArray(R.array.priority);
        ArrayAdapter<String> adapterPriority = new ArrayAdapter<>(this, R.layout.status_spinner_text, priorityArray);
        adapterPriority.setDropDownViewResource(R.layout.status_spinner_text);
        spinnerItemPriority.setAdapter(adapterPriority);

        String selectPriorityItem = SpinnerPriorityUtil.SelectIndexToItem(task_item.getPriority());
        int positionPriority = adapterPriority.getPosition(selectPriorityItem);
        spinnerItemPriority.setSelection(positionPriority);
    }
}