package com.example.cooperation.databing;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.example.cooperation.api.MyRetrofit;
import com.example.cooperation.api.RetrofitRequest_Interface;
import com.example.cooperation.constant.HttpStatus;
import com.example.cooperation.constant.SharedPreferencesConstant;
import com.example.cooperation.model.ResponseBody;
import com.example.cooperation.model.User;
import com.example.cooperation.model.UserLogin;
import com.example.cooperation.utils.DecodeJsonFromData;
import com.example.cooperation.utils.check.input.UserPasswordCheckUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPageChangePasswordDataBinding {
    private Context context;
    private ObservableField<String> observableField;

    public ActivityPageChangePasswordDataBinding(Context context) {
        this.context = context;
        this.observableField = new ObservableField<>();
    }

    public void onChangePasswordClick(View view){
        // 先检验密码有效性，再进行网络请求

        if (!UserPasswordCheckUtil.check(context, observableField.get())){
            return;
        }

        MyRetrofit.InitInstance();
        RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();

        Call<ResponseBody> responseBodyCall = retrofitRequestInterface.userModifyPassword(MyRetrofit.getToken(), observableField.get());

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();

                // 将新的密码更新到本地保存
                SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesConstant.SP_NAME, Context.MODE_PRIVATE);

                SharedPreferences.Editor edit = sharedPreferences.edit();

                edit.putString(SharedPreferencesConstant.PASSWORD,getPassword());

                edit.apply();

                // 重新登录，更新token

                MyRetrofit.InitInstance();
                RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();
                User user = MyRetrofit.getUser();

                UserLogin userLogin = new UserLogin();
                userLogin.setUsername(user.getUserName());
                userLogin.setUsername(observableField.get());

                Call<ResponseBody> login = retrofitRequestInterface.userLogin(userLogin);

                login.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if (response.isSuccessful() && response.body() != null && HttpStatus.OK.equals(response.body().getCode())){
                            // 如果登录成功，就将token存入全局
                            String token = DecodeJsonFromData.GetToken("{" + response.body().getData() + "}").getToken();
                            MyRetrofit.setToken(token);
                            // 更新本地token

                            SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesConstant.SP_NAME, Context.MODE_PRIVATE);

                            SharedPreferences.Editor edit = sharedPreferences.edit();

                            edit.putString(SharedPreferencesConstant.TOKEN,token);

                            edit.apply();

                            ((Activity)context).finish();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                        Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setPassword(String password){
        observableField.set(password);
    }

    public String getPassword(){
        return observableField.get();
    }
}
