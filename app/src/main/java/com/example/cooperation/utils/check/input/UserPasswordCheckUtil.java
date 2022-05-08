package com.example.cooperation.utils.check.input;

import android.content.Context;
import android.widget.Toast;

public class UserPasswordCheckUtil {
    public static boolean check(Context context,String password){
        if (password == null || password.length() <= 5 || password.length() >= 20){
            Toast.makeText(context,"The lenght of password should be between 5 and 20!",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
