package com.example.cooperation.utils.check.input;

import android.content.Context;
import android.widget.Toast;

import com.example.cooperation.model.ItemAdd;

public class AddItemCheckUtil {
    public static boolean check(Context context,ItemAdd item){
        if (item.getItemName() == null){
            Toast.makeText(context,"ItemName Should not be empty！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
