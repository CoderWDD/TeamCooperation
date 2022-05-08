package com.example.cooperation.utils.check.input;

import android.content.Context;
import android.widget.Toast;

import com.example.cooperation.model.User;

public class UserInfoCheckUtil {
    public static boolean check(Context context, User user){

        if (user.getNickName() == null || user.getNickName().length() == 0){
            Toast.makeText(context,"Nickname could not be empty!",Toast.LENGTH_SHORT).show();
            return false;
        }else if (user.getFirstName() == null || user.getFirstName().length() == 0){
            Toast.makeText(context,"Firstname could not be empty!",Toast.LENGTH_SHORT).show();
            return false;
        }else if (user.getLastName() == null || user.getLastName().length() == 0){
            Toast.makeText(context,"Lastname could not be empty!",Toast.LENGTH_SHORT).show();
            return false;
        }else if (user.getPhone() == null || user.getPhone().length() != 11) {
            Toast.makeText(context, "Phone wrong!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
