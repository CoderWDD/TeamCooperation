package com.example.cooperation.viewmodel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.cooperation.PageProjectDetailsActivity;
import com.example.cooperation.R;
import com.example.cooperation.api.MyRetrofit;
import com.example.cooperation.api.RetrofitRequest_Interface;
import com.example.cooperation.constant.HttpStatus;
import com.example.cooperation.databinding.FragmentPageProjectsBinding;
import com.example.cooperation.databing.adapter.RecyclerViewAdapterForProjects;
import com.example.cooperation.databing.click.ProjectItemClick;
import com.example.cooperation.model.Project;
import com.example.cooperation.model.ProjectListResponseBody;
import com.example.cooperation.model.ResponseBody;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewProjectsViewModel {
    List<Project> projectList;
    private final Context context;
    private final FragmentPageProjectsBinding fragmentPageProjectsBinding;

    public RecyclerViewProjectsViewModel(FragmentPageProjectsBinding fragmentPageProjectsBinding, Context context) {
        this.fragmentPageProjectsBinding = fragmentPageProjectsBinding;
        this.context = context;
        projectList = new LinkedList<>();
    }

    public void refreshRecyclerViewItems() {

        MyRetrofit.InitInstance();
        RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();

        Call<ProjectListResponseBody> projectListResponseBodyCall = retrofitRequestInterface.projectGetList(MyRetrofit.getToken());


        projectListResponseBodyCall.enqueue(new Callback<ProjectListResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ProjectListResponseBody> call, @NonNull Response<ProjectListResponseBody> response) {
                if (response.isSuccessful() && response.body() != null && HttpStatus.OK.equals(response.body().getCode())) {
                    Project[] temp = response.body().getData();
                    projectList.addAll(Arrays.asList(temp));

                    RecyclerViewAdapterForProjects recyclerViewAdapterForProjects = new RecyclerViewAdapterForProjects(projectList, new ProjectItemClick() {
                        @Override
                        public void onClicked(View view, Project project) {
                            Intent intent = new Intent(context, PageProjectDetailsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("project_item", project);
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                        }

                        @Override
                        public boolean onLongClicked(View view, final Project project) {
                            // 弹出删除该当前item
                            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                            dialog.setTitle(R.string.attention);
                            dialog.setMessage(R.string.delete_message);

                            dialog.setPositiveButton(R.string.delete_positive_button, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // 确定删除
                                    RetrofitRequest_Interface retrofitRequestInterface1 = MyRetrofit.getRetrofitRequestInterface();
                                    Call<ResponseBody> responseBodyCall = retrofitRequestInterface1.projectDelete(MyRetrofit.getToken(), project.getProjectId());

                                    // 删除project
                                    responseBodyCall.enqueue(new Callback<ResponseBody>() {
                                        @Override
                                        public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                                            if (response.isSuccessful() && response.body() != null && HttpStatus.OK.equals(response.body().getCode())){
                                                Toast.makeText(context,response.body().getData(),Toast.LENGTH_SHORT).show();
                                            }else {
                                                Toast.makeText(context,"Something wrong!",Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                                            Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                            dialog.setNegativeButton(R.string.delete_negative_button, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });

                            // TODO 将Dialog的样式自定义好看点
                            // 参考：https://blog.csdn.net/xiayiye5/article/details/83080623
                            dialog.create();
                            dialog.show();
                            dialog.getContext().setTheme(R.style.Theme_Cooperation);
                            return false;
                        }
                    });

                    fragmentPageProjectsBinding.recyclerviewProjects.setAdapter(recyclerViewAdapterForProjects);

                }
            }

            @Override
            public void onFailure(@NonNull Call<ProjectListResponseBody> call, @NonNull Throwable t) {
                Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
