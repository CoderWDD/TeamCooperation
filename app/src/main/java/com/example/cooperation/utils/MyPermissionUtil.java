package com.example.cooperation.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import com.example.cooperation.constant.PermissionConstant;

import java.util.List;

public class MyPermissionUtil {
    public static boolean checkPermissions(Context context, List<String> permissions){
        for (String e : permissions){
            if (context.checkSelfPermission(e) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    public static boolean requestPermission(Context context,List<String> permissions){
        String[] strings = permissions.toArray(new String[0]);

//        for (String e : permissions){
//            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)context, e)){
//                Log.i("myTag", "requestPermission: should show request ");
//                AlertDialog dialog = new AlertDialog.Builder(context).setTitle("读写权限").setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        ActivityCompat.requestPermissions((Activity) context,new String[]{e},PermissionConstant.MY_PERMISSION_REQUEST_CODE);
//                    }
//                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        ActivityCompat.requestPermissions((Activity) context,new String[]{e},PermissionConstant.MY_PERMISSION_REQUEST_CODE);
//                    }
//                }).show();
//            }
//        }



        ActivityCompat.requestPermissions((Activity) context,strings, PermissionConstant.MY_PERMISSION_REQUEST_CODE);

        if (checkPermissions(context,permissions)){
            return true;
        }else {
            return false;
        }
    }

}
