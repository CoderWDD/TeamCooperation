package com.example.cooperation.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
import com.example.cooperation.utils.SelectListByStatusUtil;

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
    private final SwipeRefreshLayout swipeRefreshLayout;

    public RecyclerViewProjectsViewModel(FragmentPageProjectsBinding fragmentPageProjectsBinding, Context context,SwipeRefreshLayout swipeRefreshLayout) {
        this.fragmentPageProjectsBinding = fragmentPageProjectsBinding;
        this.context = context;
        projectList = new LinkedList<>();
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    public void refreshRecyclerViewItems() {
        // 防止刷新后旧数据还在
        projectList.clear();
        MyRetrofit.InitInstance();
        RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();

        Call<ProjectListResponseBody> projectListResponseBodyCall = retrofitRequestInterface.projectGetList(MyRetrofit.getToken());

        projectListResponseBodyCall.enqueue(new Callback<ProjectListResponseBody>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NonNull Call<ProjectListResponseBody> call, @NonNull Response<ProjectListResponseBody> response) {
                if (response.isSuccessful() && response.body() != null && HttpStatus.OK.equals(response.body().getCode())) {
                    Project[] temp = response.body().getData();
                    projectList.addAll(Arrays.asList(temp));

                    // 获取要显示的是哪种状态的project，并显示
                    AppCompatCheckBox projectTodo = fragmentPageProjectsBinding.getRoot().findViewById(R.id.project_status_todo);

                    AppCompatCheckBox projectDoing = fragmentPageProjectsBinding.getRoot().findViewById(R.id.project_status_doing);

                    AppCompatCheckBox projectDone = fragmentPageProjectsBinding.getRoot().findViewById(R.id.project_status_done);
                    List<Project> showList;
                    if (projectTodo == null || projectDoing == null || projectDone == null){
                        Toast.makeText(context,"Something wrong!",Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        showList = SelectListByStatusUtil.selectProjects(RecyclerViewProjectsViewModel.this.projectList, projectTodo.isChecked(), projectDoing.isChecked(), projectDone.isChecked());
                    }

                    RecyclerViewAdapterForProjects recyclerViewAdapterForProjects = new RecyclerViewAdapterForProjects(showList, new ProjectItemClick() {
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

                            dialog.setPositiveButton(R.string.delete_positive_button, (dialogInterface, i) -> {
                                // 确定删除
                                RetrofitRequest_Interface retrofitRequestInterface1 = MyRetrofit.getRetrofitRequestInterface();
                                Call<ResponseBody> responseBodyCall = retrofitRequestInterface1.projectDelete(MyRetrofit.getToken(), project.getProjectId());

                                // 删除project
                                responseBodyCall.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(@NonNull Call<ResponseBody> call1, @NonNull Response<ResponseBody> response1) {
                                        if (response1.isSuccessful() && response1.body() != null && HttpStatus.OK.equals(response1.body().getCode())){
                                            Toast.makeText(context, response1.body().getData(),Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(context,"Something wrong!",Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<ResponseBody> call1, @NonNull Throwable t) {
                                        Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show();
                                    }
                                });
                            });
                            dialog.setNegativeButton(R.string.delete_negative_button, (dialogInterface, i) -> {

                            });

                            // TODO 将Dialog的样式自定义好看点
                            // 参考：https://blog.csdn.net/xiayiye5/article/details/83080623
                            dialog.create();
                            dialog.show();
                            return false;
                        }
                    });

                    if (swipeRefreshLayout != null){
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    fragmentPageProjectsBinding.recyclerviewProjects.setAdapter(recyclerViewAdapterForProjects);

                }
            }

            @Override
            public void onFailure(@NonNull Call<ProjectListResponseBody> call, @NonNull Throwable t) {
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
