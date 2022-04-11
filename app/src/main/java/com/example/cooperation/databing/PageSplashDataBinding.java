package com.example.cooperation.databing;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.cooperation.LoginPageActivity;

public class PageSplashDataBinding {
    private Context context;

    public PageSplashDataBinding(Context context){
        this.context = context;
    }

    public void onSkipClicked(View view){
        Intent intent = new Intent(context, LoginPageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
