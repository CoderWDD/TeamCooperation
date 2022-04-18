package com.example.cooperation.databing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableField;

import com.example.cooperation.SignUpPageActivity;
import com.example.cooperation.api.MyRetrofit;
import com.example.cooperation.api.RetrofitRequest_Interface;
import com.example.cooperation.constant.HttpStatus;
import com.example.cooperation.model.ResponseBody;
import com.example.cooperation.model.UserLogin;
import com.example.cooperation.utils.DecodeJsonFromData;
import com.example.cooperation.utils.check.input.UserLoginCheckUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageLoginDataBinding {
    private Context context;
    private ObservableField<UserLogin> loginObservableField;

    public PageLoginDataBinding(Context context){
        this.context = context;
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername("");
        userLogin.setPassword("");
        loginObservableField = new ObservableField<>();
        loginObservableField.set(userLogin);
    }

    public String getUsername(){
        return loginObservableField.get().getUsername();
    }

    public String getPassword(){
        return loginObservableField.get().getPassword();
    }

    public void setUsername(String username){
        loginObservableField.get().setUsername(username);
    }

    public void setPassword(String password){
        loginObservableField.get().setPassword(password);
    }

    public void onLoginClicked(View view){

        // 如果账号密码长度不合格，就提示，不进行网络请求
        if (!UserLoginCheckUtil.check(context,getUsername(),getPassword())) {
            return;
        }

        // 调用网络请求，进行登录操作
        MyRetrofit.InitInstance();
        RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();
        UserLogin userLogin = loginObservableField.get();

        Call<ResponseBody> login = retrofitRequestInterface.userLogin(userLogin);

        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && HttpStatus.OK.equals(response.body().getCode())){
                    // 如果登录成功，就将token存入全局
                    String token = DecodeJsonFromData.GetToken("{" + response.body().getData() + "}").getToken();
                    MyRetrofit.setToken(token);
                    // TODO 登录成功后，将账号密码，token，进行本地保存

                    // TODO 登录成功后，跳转到主页面
                }
                Toast.makeText(context,response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onSignUpClicked(View view){
        // TODO 页面调整，启动方式不用设置，但需要接收注册成功后的账号信息
        Intent intent = new Intent(context, SignUpPageActivity.class);
        Activity activity = (Activity) context;
        activity.startActivityForResult(intent,1);
    }

}
