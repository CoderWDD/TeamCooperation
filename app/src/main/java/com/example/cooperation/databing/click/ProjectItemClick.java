package com.example.cooperation.databing.click;

import android.view.View;

import com.example.cooperation.model.Project;

public interface ProjectItemClick {
    void onClicked(View view, final Project project);
    boolean onLongClicked(View view,final Project project);
}
