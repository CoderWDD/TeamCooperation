package com.example.cooperation.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    public String DateFormat(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Log.i("myTag", "DateFormat: " + simpleDateFormat.format(date));
        return simpleDateFormat.format(new Date());
    }
}
