package com.example.cooperation.databing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.cooperation.ActivityPageAddProject;
import com.example.cooperation.ActivityPageJoinProject;

public class ActivityFloatingActionButtonDataBinding {
    private Context context;

    public ActivityFloatingActionButtonDataBinding(Context context) {
        this.context = context;
    }

    public void onAddProjectClicked(View view){
        Intent intent = new Intent(context, ActivityPageAddProject.class);
        ((Activity)context).startActivity(intent);
    }

    public void onJoinToProjectClicked(View view){
        Intent intent = new Intent(context, ActivityPageJoinProject.class);
        ((Activity)context).startActivity(intent);
    }

}
