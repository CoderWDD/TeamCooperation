package com.example.cooperation.utils;

import com.example.cooperation.constant.Status;
import com.example.cooperation.model.ItemAdd;
import com.example.cooperation.model.Project;

import java.util.LinkedList;
import java.util.List;

public class SelectListByStatusUtil {
    public static List<Project> selectProjects(List<Project> originList,boolean todo,boolean doing,boolean done){
        List<Project> res = new LinkedList<>();
        for (Project e : originList){
            if (todo && Status.TODO.equals(e.getStatus())){
                res.add(e);
            }
            if (doing && Status.DOING.equals(e.getStatus())){
                res.add(e);
            }
            if (done && Status.DONE.equals(e.getStatus())){
                res.add(e);
            }
        }
        return res;
    }

    public static List<ItemAdd> selectItems(List<ItemAdd> originList,boolean todo,boolean doing,boolean done){
        List<ItemAdd> res = new LinkedList<>();

        for (ItemAdd e : originList){
            if (todo && Status.TODO.equals(e.getStatus())){
                res.add(e);
            }
            if (doing && Status.DOING.equals(e.getStatus())){
                res.add(e);
            }
            if (done && Status.DONE.equals(e.getStatus())){
                res.add(e);
            }
        }

        return res;
    }
}
