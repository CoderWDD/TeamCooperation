package com.example.cooperation.databing.click;

import android.view.View;

import com.example.cooperation.model.ItemAdd;

public interface TaskItemClicked {
    void onClicked(View view, ItemAdd item);
    boolean onLongClicked(View view,final ItemAdd itemAdd);
}
