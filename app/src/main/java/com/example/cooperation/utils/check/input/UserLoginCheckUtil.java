package com.example.cooperation.utils.check.input;

import android.content.Context;
import android.widget.Toast;

public class UserLoginCheckUtil {
    public static boolean check(Context context,String username,String password){
        if (username.length() <= 5 || username.length() >= 20){
            Toast.makeText(context,"账号长度不合格！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() <= 5 || password.length() >= 20){
            Toast.makeText(context,"密码长度不合格！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
