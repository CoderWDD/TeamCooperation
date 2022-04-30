package com.example.cooperation.databing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cooperation.ActivityPageAddItem;
import com.example.cooperation.model.Project;

public class PageProjectDetailsDataBinding {
    private Context context;

    public PageProjectDetailsDataBinding(Context context) {
        this.context = context;
    }

    public void onAddItemClick(View view, Project project){
        Intent intent = new Intent(context, ActivityPageAddItem.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("project",project);
        context.startActivity(intent,bundle);
    }

    public void onModifyClick(View view,Project project){
        // TODO 网络请求，修改project
    }
}
