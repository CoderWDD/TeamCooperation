package com.example.cooperation.databing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.cooperation.ActivityFloatingActionButtonChoose;


public class FragmentProjectsDataBinding{
    private Context context;

    public FragmentProjectsDataBinding(Context context) {
        this.context = context;
    }

    public void onFloatingActionButtonClicked(View view){
        // TODO 弹出一个框，让选择添加projects还是加入别人的projects
        Toast.makeText(context,"asdasdasd",Toast.LENGTH_SHORT).show();

//        Intent intent = new Intent(context, PageProjectDetailsActivity.class);
//
//        ((Activity) context).startActivity(intent);

//        FragmentManager supportFragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
//
//        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
//
////        fragmentTransaction.add(R.id.fragment_container,new FragmentFloatingActionButtonChoose());
//
//        fragmentTransaction.attach(new FragmentFloatingActionButtonChoose());
//
//        fragmentTransaction.commit();
//
//        ((Activity)context).getLayoutInflater().inflate(R.layout.fragment_floating_action_button_choose,(ViewGroup) view.getRootView(),true);


        Intent intent = new Intent(context, ActivityFloatingActionButtonChoose.class);

        ((Activity)context).startActivity(intent);

    }
}
