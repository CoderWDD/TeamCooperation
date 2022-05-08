package com.example.cooperation.databing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import com.example.cooperation.ActivityPageChangeUserPassword;
import com.example.cooperation.api.MyRetrofit;
import com.example.cooperation.api.RetrofitRequest_Interface;
import com.example.cooperation.constant.HttpStatus;
import com.example.cooperation.constant.SharedPreferencesConstant;
import com.example.cooperation.model.ResponseBody;
import com.example.cooperation.model.User;
import com.example.cooperation.utils.check.input.UserInfoCheckUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPageUserInfoDataBinding {

    private Context context;

    public ActivityPageUserInfoDataBinding(Context context,User user) {
        this.context = context;
    }

    public void onModifyClick(View view,final User user){
        // 先检验信息有效性，再进行网络请求

        if (!UserInfoCheckUtil.check(context,user)){
            return;
        }

        MyRetrofit.InitInstance();

        RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();

        Call<ResponseBody> responseBodyCall = retrofitRequestInterface.userModify(MyRetrofit.getToken(), user);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null && HttpStatus.OK.equals(response.body().getCode())){

                    SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesConstant.SP_NAME, Context.MODE_PRIVATE);

                    SharedPreferences.Editor edit = sharedPreferences.edit();

                    edit.putString(SharedPreferencesConstant.NICKNAME,user.getNickName());

                    edit.putString(SharedPreferencesConstant.FIRSTNAME,user.getFirstName());

                    edit.putString(SharedPreferencesConstant.LASTNAME,user.getLastName());

                    edit.putString(SharedPreferencesConstant.PHONE,user.getPhone());

                    edit.putString(SharedPreferencesConstant.DEPARTMENT,user.getDepartment());

                    edit.putString(SharedPreferencesConstant.DESCRIPTION,user.getDescription());

                    edit.apply();

                    Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    // 如果修改成功，就更新用户信息
                    MyRetrofit.setUser(user);
                }else {
                    Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onChangePasswordClick(View view){
        // 弹出新密码输入框，修改用户密码

        Intent intent = new Intent(context, ActivityPageChangeUserPassword.class);

        context.startActivity(intent);


    }



}
