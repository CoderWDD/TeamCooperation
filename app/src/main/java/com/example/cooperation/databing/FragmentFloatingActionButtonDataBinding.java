package com.example.cooperation.databing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.cooperation.PageProjectDetailsActivity;

public class FragmentFloatingActionButtonDataBinding {
    private Context context;

    public FragmentFloatingActionButtonDataBinding(Context context) {
        this.context = context;
    }

    public void onAddProjectClicked(View view){
        Intent intent = new Intent(context, PageProjectDetailsActivity.class);
        ((Activity)context).startActivity(intent);
    }

    public void onJoinToProjectClicked(View view){

    }

}
