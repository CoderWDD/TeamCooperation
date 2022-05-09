package com.example.cooperation.utils;

public class SpinnerPriorityUtil {

    public static int SelectItemToIndex(String selectItem){
        if ("重要紧急".equals(selectItem)){
            return 1;
        } else if ("重要不紧急".equals(selectItem)){
            return 2;
        }else if ("不重要紧急".equals(selectItem)){
            return 3;
        }else if ("不重要不紧急".equals(selectItem)){
            return 4;
        }
        // 如果信息有误，则默认为”重要紧急“
        return 1;
    }

    public static String SelectIndexToItem(int index){
        if (index == 1){
            return "重要紧急";
        }else if (index == 2){
            return "重要不紧急";
        }else if (index == 3){
            return "不重要紧急";
        }else if (index == 4){
            return "不重要不紧急";
        }
        return "重要紧急";
    }
}
