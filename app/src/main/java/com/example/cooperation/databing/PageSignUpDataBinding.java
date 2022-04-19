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
import com.example.cooperation.model.User;
import com.example.cooperation.utils.check.input.UserSignUpCheckUtil;

import java.util.Date;

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
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && HttpStatus.OK.equals(response.body().getCode())){
                    Toast.makeText(context,"注册成功！",Toast.LENGTH_SHORT).show();
                    // TODO 将注册的用户名和用户密码返回，方便登录
                    ((Activity)context).finish();
                }else {
                    Toast.makeText(context,response.body().getData(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context,"注册失败！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getNickName() {
        return observableField.get().getNickName();
    }

    public void setNickName(String nickName) {
        observableField.get().setNickName(nickName);
    }

    public String getFirstName() {
        return observableField.get().getFirstName();
    }

    public void setFirstName(String firstName) {
        observableField.get().setFirstName(firstName);
    }

    public String getLastName() {
        return observableField.get().getLastName();
    }

    public void setLastName(String lastName) {
        observableField.get().setLastName(lastName);
    }

    public String getDepartment() {
        return observableField.get().getDepartment();
    }

    public void setDepartment(String department) {
        observableField.get().setDepartment(department);
    }

    public String getPhone() {
        return observableField.get().getPhone();
    }

    public void setPhone(String phone) {
        observableField.get().setPhone(phone);
    }

    public String getUserName() {
        return observableField.get().getUserName();
    }

    public void setUserName(String userName) {
        observableField.get().setUserName(userName);
    }

    public String getPassword() {
        return observableField.get().getPassword();
    }

    public void setPassword(String password) {
        observableField.get().setPassword(password);
    }

    public Date getCreateTime() {
        return observableField.get().getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        observableField.get().setCreateTime(createTime);
    }

    public int getSex() {
        return observableField.get().getSex();
    }

    public void setSex(int sex) {
        observableField.get().setSex(sex);
    }

    public String getDescription() {
        return observableField.get().getDescription();
    }

    public void setDescription(String description) {
        observableField.get().setDescription(description);
    }

    public void setAvatar(byte[] avatar){
        observableField.get().setAvatar(avatar);
    }

    public byte[] getAvatar(){
        return observableField.get().getAvatar();
    }

}
