package com.example.cooperation.databing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cooperation.ActivityPageAddItem;
import com.example.cooperation.R;
import com.example.cooperation.api.MyRetrofit;
import com.example.cooperation.api.RetrofitRequest_Interface;
import com.example.cooperation.constant.HttpStatus;
import com.example.cooperation.model.Project;
import com.example.cooperation.model.ProjectModifyInfo;
import com.example.cooperation.model.ResponseBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageProjectDetailsDataBinding {
    private Context context;

    public PageProjectDetailsDataBinding(Context context) {
        this.context = context;
    }

    public void onAddItemClick(View view, Project project){
        Intent intent = new Intent(context, ActivityPageAddItem.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("project",project);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public void onModifyClick(View view,Project project){
        // 网络请求，修改project

        Spinner spinner = (Spinner)((Activity) context).findViewById(R.id.spinner_project_status);
        String selectedItem = (String)spinner.getSelectedItem();

        ProjectModifyInfo projectModifyInfo = new ProjectModifyInfo();
        projectModifyInfo.setProjectName(project.getProjectName());
        projectModifyInfo.setDescription(project.getDescription());
        projectModifyInfo.setStatus(selectedItem);
        projectModifyInfo.setProjectTime(project.getProjectTime());

        MyRetrofit.InitInstance();
        RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();

        Call<ResponseBody> responseBodyCall = retrofitRequestInterface.projectModify(MyRetrofit.getToken(), projectModifyInfo, project.getProjectId());

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null && HttpStatus.OK.equals(response.body().getCode())){
                    Toast.makeText(context,response.body().getData(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });


    }
}
