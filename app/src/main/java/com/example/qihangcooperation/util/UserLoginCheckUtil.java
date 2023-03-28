package com.example.qihangcooperation.util;

import android.content.Context;
import android.widget.Toast;

public class UserLoginCheckUtil {
    public static boolean check(Context context, String username, String password){
        if (username == null || password == null){
            Toast.makeText(context,"账号或密码不能为空！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() <= 5 || password.length() >= 20){
            Toast.makeText(context,"密码长度不合格！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}