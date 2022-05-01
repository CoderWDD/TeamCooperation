package com.example.cooperation;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cooperation.databinding.ActivityPageAddItemBinding;

public class ActivityPageAddItem extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除系统状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        ActivityPageAddItemBinding activityPageAddItemBinding = DataBindingUtil.setContentView(this, R.layout.activity_page_add_item);

        Spinner spinner = findViewById(R.id.spinner_status);

        String[] stringArray = getResources().getStringArray(R.array.status);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.status_spinner_text, stringArray);

        adapter.setDropDownViewResource(R.layout.status_spinner_text);

        spinner.setAdapter(adapter);
    }
}