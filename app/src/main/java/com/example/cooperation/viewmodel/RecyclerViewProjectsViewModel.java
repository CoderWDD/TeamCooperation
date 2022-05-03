package com.example.cooperation.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cooperation.PageProjectDetailsActivity;
import com.example.cooperation.api.MyRetrofit;
import com.example.cooperation.api.RetrofitRequest_Interface;
import com.example.cooperation.databinding.FragmentPageProjectsBinding;
import com.example.cooperation.databing.adapter.RecyclerViewAdapterForProjects;
import com.example.cooperation.databing.click.ProjectItemClick;
import com.example.cooperation.model.Project;
import com.example.cooperation.model.ProjectListResponseBody;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewProjectsViewModel {
    List<Project> projectList;
    private Context context;
    private FragmentPageProjectsBinding fragmentPageProjectsBinding;
    public RecyclerViewProjectsViewModel(FragmentPageProjectsBinding fragmentPageProjectsBinding,Context context) {
        this.fragmentPageProjectsBinding = fragmentPageProjectsBinding;
        this.context = context;
        projectList = new LinkedList<>();
    }

    public void refreshRecyclerViewItems(){

        MyRetrofit.InitInstance();
        RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();

        Call<ProjectListResponseBody> projectListResponseBodyCall = retrofitRequestInterface.projectGetList(MyRetrofit.getToken());


        projectListResponseBodyCall.enqueue(new Callback<ProjectListResponseBody>() {
            @Override
            public void onResponse(Call<ProjectListResponseBody> call, Response<ProjectListResponseBody> response) {
                if (response.isSuccessful()){
                    Project[] temp = response.body().getData();
                    for (Project e : temp){
                        projectList.add(e);
                    }

                    RecyclerViewAdapterForProjects recyclerViewAdapterForProjects = new RecyclerViewAdapterForProjects(projectList, new ProjectItemClick() {
                        @Override
                        public void onClicked(View view, final Project project) {
                            Intent intent = new Intent(context, PageProjectDetailsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("project_item", project);
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                        }
                    });

                    fragmentPageProjectsBinding.recyclerviewProjects.setAdapter(recyclerViewAdapterForProjects);

                }
            }

            @Override
            public void onFailure(Call<ProjectListResponseBody> call, Throwable t) {

            }
        });



    }


    public List<Project> getProjects(){
        // TODO 网络请求，获取projects列表


        MyRetrofit.InitInstance();
        RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();

        Call<ProjectListResponseBody> projectListResponseBodyCall = retrofitRequestInterface.projectGetList(MyRetrofit.getToken());


        projectListResponseBodyCall.enqueue(new Callback<ProjectListResponseBody>() {
            @Override
            public void onResponse(Call<ProjectListResponseBody> call, Response<ProjectListResponseBody> response) {
                if (response.isSuccessful()){
                    Project[] temp = response.body().getData();
                    for (Project e : temp){
                        projectList.add(e);
                    }


                }
            }

            @Override
            public void onFailure(Call<ProjectListResponseBody> call, Throwable t) {

            }
        });



        return projectList;
    }
}
