package com.example.cooperation.viewmodel;

import android.util.Log;

import com.example.cooperation.api.MyRetrofit;
import com.example.cooperation.api.RetrofitRequest_Interface;
import com.example.cooperation.constant.HttpStatus;
import com.example.cooperation.model.Project;
import com.example.cooperation.model.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewProjectsViewModel {
    public List<Project> getProjects(){
        List<Project> projectList = new ArrayList<>();

        // TODO 网络请求，获取projects列表

        MyRetrofit.InitInstance();
        RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();

        Call<ResponseBody> responseBodyCall = retrofitRequestInterface.projectGetList(MyRetrofit.getToken());

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    if (HttpStatus.OK.equals(response.body().getCode())){
                        Log.i("myTag", "start");
                    }
                    String data = response.body().getData();

                    Log.i("myTag", "onResponse: " + data);

                }else {

                    return;
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        Project project = new Project();
        project.setProjectId(12);
        project.setProjectName("Help");
        project.setProjectTime(new Date());
        project.setCreator("吴某人");
        project.setDescription("nothing");
        project.setStatus("Done");
        project.setInvitationCode("asdasd");


        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        projectList.add(project);
        return projectList;
    }
}
