package com.example.cooperation.databing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.example.cooperation.api.MyRetrofit;
import com.example.cooperation.api.RetrofitRequest_Interface;
import com.example.cooperation.constant.ActivityRequestCodeConstant;
import com.example.cooperation.constant.HttpStatus;
import com.example.cooperation.model.ResponseBody;
import com.example.cooperation.model.User;
import com.example.cooperation.utils.check.input.UserSignUpCheckUtil;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageSignUpDataBinding {
    private Context context;
    private ObservableField<User> observableField;

    public PageSignUpDataBinding(Context context) {
        this.context = context;
        User user = new User();
        observableField = new ObservableField<>();
        observableField.set(user);
    }

    public void onSignUpClicked(View view){
        // 如果有长度不合格的，就提示，不进行网络请求
        if (!UserSignUpCheckUtil.check(context,getUserName(),getPassword(),getFirstName(),getLastName(),getDepartment(),getPhone())) {
            return ;
        }

        MyRetrofit.InitInstance();
        RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();

        User user = observableField.get();

        Call<ResponseBody> userRegister = retrofitRequestInterface.userRegister(user);

        userRegister.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful() && HttpStatus.OK.equals(Objects.requireNonNull(response.body()).getCode())){
                    Toast.makeText(context,"注册成功！",Toast.LENGTH_SHORT).show();
                    // 将注册的用户名和用户密码返回，方便登录
                    Intent intent = new Intent();
                    intent.putExtra("username",getUserName());
                    intent.putExtra("password",getPassword());
                    ((Activity)context).setResult(ActivityRequestCodeConstant.SIGNUP,intent);

                    ((Activity)context).finish();
                }else {
                    assert response.body() != null;
                    Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Toast.makeText(context,"注册失败！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getNickName() {
        return Objects.requireNonNull(observableField.get()).getNickName();
    }

    public void setNickName(String nickName) {
        Objects.requireNonNull(observableField.get()).setNickName(nickName);
    }

    public String getFirstName() {
        return Objects.requireNonNull(observableField.get()).getFirstName();
    }

    public void setFirstName(String firstName) {
        Objects.requireNonNull(observableField.get()).setFirstName(firstName);
    }

    public String getLastName() {
        return Objects.requireNonNull(observableField.get()).getLastName();
    }

    public void setLastName(String lastName) {
        Objects.requireNonNull(observableField.get()).setLastName(lastName);
    }

    public String getDepartment() {
        return Objects.requireNonNull(observableField.get()).getDepartment();
    }

    public void setDepartment(String department) {
        Objects.requireNonNull(observableField.get()).setDepartment(department);
    }

    public String getPhone() {
        return Objects.requireNonNull(observableField.get()).getPhone();
    }

    public void setPhone(String phone) {
        Objects.requireNonNull(observableField.get()).setPhone(phone);
    }

    public String getUserName() {
        return Objects.requireNonNull(observableField.get()).getUserName();
    }

    public void setUserName(String userName) {
        Objects.requireNonNull(observableField.get()).setUserName(userName);
    }

    public String getPassword() {
        return Objects.requireNonNull(observableField.get()).getPassword();
    }

    public void setPassword(String password) {
        Objects.requireNonNull(observableField.get()).setPassword(password);
    }

    public String getCreateTime() {
        return Objects.requireNonNull(observableField.get()).getCreateTime();
    }

    public void setCreateTime(String createTime) {
        Objects.requireNonNull(observableField.get()).setCreateTime(createTime);
    }

    public String getSex() {
        return Objects.requireNonNull(observableField.get()).getSex();
    }

    public void setSex(String sex) {
        Objects.requireNonNull(observableField.get()).setSex(sex);
    }

    public String getDescription() {
        return Objects.requireNonNull(observableField.get()).getDescription();
    }

    public void setDescription(String description) {
        Objects.requireNonNull(observableField.get()).setDescription(description);
    }

    public void setAvatar(byte[] avatar){
        Objects.requireNonNull(observableField.get()).setAvatar(avatar);
    }

    public byte[] getAvatar(){
        return Objects.requireNonNull(observableField.get()).getAvatar();
    }

}
