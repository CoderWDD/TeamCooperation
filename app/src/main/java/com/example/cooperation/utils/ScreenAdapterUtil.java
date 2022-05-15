package com.example.cooperation.utils;

import android.content.Context;
import android.util.TypedValue;

public class ScreenAdapterUtil {
    public static int dp2px(Context ctx, float dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, ctx.getResources().getDisplayMetrics());
    }
}
