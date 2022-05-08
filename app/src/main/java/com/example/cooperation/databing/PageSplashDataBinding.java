package com.example.cooperation.databing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import com.example.cooperation.LoginPageActivity;
import com.example.cooperation.constant.SharedPreferencesConstant;

public class PageSplashDataBinding {
    private Context context;

    public PageSplashDataBinding(Context context){
        this.context = context;
    }

    public void onSkipClicked(View view){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesConstant.SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        // 点击了跳过，就不是第一次使用了
        edit.putBoolean(SharedPreferencesConstant.FIRST_USE,false);
        edit.apply();

        Intent intent = new Intent(context, LoginPageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
