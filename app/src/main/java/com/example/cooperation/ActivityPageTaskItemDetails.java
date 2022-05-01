package com.example.cooperation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cooperation.databinding.ActivityPageTaskItemDetailsBinding;
import com.example.cooperation.model.ItemAdd;

public class ActivityPageTaskItemDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPageTaskItemDetailsBinding activityPageTaskItemDetails = DataBindingUtil.setContentView(this, R.layout.activity_page_task_item_details);

        // 取出Bundle里的item，key：task_item

        Bundle bundle = getIntent().getExtras();

        ItemAdd task_item = (ItemAdd) bundle.getSerializable("task_item");

        activityPageTaskItemDetails.setItemDetails(task_item);

    }
}