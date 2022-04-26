package com.example.cooperation.utils.check.input;

import android.content.Context;
import android.widget.Toast;

public class UserSignUpCheckUtil {
    public static boolean check(Context context,String username,String password,String firstName,String lastName,String department,String phoneNumber){
        if (username == null || password == null || firstName == null || lastName == null || department == null || phoneNumber == null){
            Toast.makeText(context,"信息项不能留空！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (username.length() <= 5 || username.length() >= 20){
            Toast.makeText(context,"账号长度必须在5~20之间！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() <= 5 || password.length() >= 20){
            Toast.makeText(context,"密码长度必须在5~20之间！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (firstName.length() >= 10){
            Toast.makeText(context,"FirstName长度必须大于10！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (lastName.length() >= 10){
            Toast.makeText(context,"LastName长度必须大于10！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (department.length() >= 10){
            Toast.makeText(context,"Department长度必须大于10！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phoneNumber.length() != 11){
            Toast.makeText(context,"PhoneNumber长度必须为11！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
