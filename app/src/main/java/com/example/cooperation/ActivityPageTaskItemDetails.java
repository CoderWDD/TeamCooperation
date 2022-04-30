package com.example.cooperation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cooperation.databinding.ActivityPageTaskItemDetailsBinding;

public class ActivityPageTaskItemDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPageTaskItemDetailsBinding activityPageTaskItemDetails = DataBindingUtil.setContentView(this, R.layout.activity_page_task_item_details);

        // TODO 取出Bundle里的item，key：task_item

        // TODO 添加双向绑定和databing
    }
}