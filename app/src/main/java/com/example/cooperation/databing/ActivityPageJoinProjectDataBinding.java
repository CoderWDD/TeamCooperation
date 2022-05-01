package com.example.cooperation.databing;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableField;

import com.example.cooperation.api.MyRetrofit;
import com.example.cooperation.api.RetrofitRequest_Interface;
import com.example.cooperation.constant.HttpStatus;
import com.example.cooperation.model.ResponseBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPageJoinProjectDataBinding {
    private Context context;
    private ObservableField<String> observableField;

    public ActivityPageJoinProjectDataBinding(Context context) {
        this.context = context;
        observableField = new ObservableField<>();
        observableField.set("");
    }

    public void onJoinProjectClick(View view){
        MyRetrofit.InitInstance();
        RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();

        String code = observableField.get();

        if (code == null || code.length() != 6){
            Toast.makeText(context,"Code should be checked!",Toast.LENGTH_SHORT).show();
            return;
        }

        Call<ResponseBody> responseBodyCall = retrofitRequestInterface.projectJoinProject(MyRetrofit.getToken(), code);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && HttpStatus.OK.equals(response.body().getCode())){
                    // 如果加入成功
                    Toast.makeText(context,"加入成功！",Toast.LENGTH_SHORT).show();
                    ((Activity)context).finish();
                }else {
                    Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context,"加入失败！",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setInvitationCode(String invitationCode){
        observableField.set(invitationCode);
    }

    public String getInvitationCode(){
        return observableField.get();
    }
}
