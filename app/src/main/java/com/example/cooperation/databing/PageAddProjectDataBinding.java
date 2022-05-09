package com.example.cooperation.databing;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableField;

import com.example.cooperation.api.MyRetrofit;
import com.example.cooperation.api.RetrofitRequest_Interface;
import com.example.cooperation.constant.HttpStatus;
import com.example.cooperation.model.Project;
import com.example.cooperation.model.ProjectCreate;
import com.example.cooperation.model.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageAddProjectDataBinding {
    private Context context;
    private ObservableField<Project> projectObservableField;

    public PageAddProjectDataBinding(Context context) {
        this.context = context;
        Project project = new Project();
        projectObservableField = new ObservableField<>();
        projectObservableField.set(project);
    }

    public void setProjectName(String projectName) {
        this.projectObservableField.get().setProjectName(projectName);
    }

    public String getProjectName() {
        return this.projectObservableField.get().getProjectName();
    }

    public String getDescription() {
        return this.projectObservableField.get().getDescription();
    }

    public void setDescription(String description) {
        this.projectObservableField.get().setDescription(description);
    }

    public void setPriority(int priority){
        this.projectObservableField.get().setPriority(priority);
    }

    public int getPriority(){
        return this.projectObservableField.get().getPriority();
    }

    public void onCreateProjectClicked(View view){
        // 网络请求，创建项目

        if (projectObservableField.get().getProjectName() == null){
            Toast.makeText(context,"Project Name Can't Be Empty!",Toast.LENGTH_SHORT).show();
            return;
        }

        MyRetrofit.InitInstance();

        RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();

        // 包装要创建的project对象
        ProjectCreate projectCreate = new ProjectCreate();

        projectCreate.setProjectName(projectObservableField.get().getProjectName());

        projectCreate.setDescription(projectObservableField.get().getDescription());

        projectCreate.setPriority(projectObservableField.get().getPriority() + 1);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        projectCreate.setCreateTime(simpleDateFormat.format(new Date()));

        Call<ResponseBody> responseBodyCall = retrofitRequestInterface.projectCreate(MyRetrofit.getToken(), projectCreate);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null && HttpStatus.OK.equals(response.body().getCode())){
                    // 如果创建成功
                    Toast.makeText(context,"创建成功！",Toast.LENGTH_SHORT).show();
                    ((Activity)context).finish();
                }else {
                    if (response.body() == null){
                        Toast.makeText(context,"出错啦！",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context,"创建失败！",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
