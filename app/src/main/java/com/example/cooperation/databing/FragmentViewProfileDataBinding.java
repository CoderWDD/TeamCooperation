package com.example.cooperation.databing;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.databinding.ObservableField;

import com.example.cooperation.ActivityPageModifyUserInfo;
import com.example.cooperation.api.MyRetrofit;
import com.example.cooperation.model.User;

public class FragmentViewProfileDataBinding {
    private Context context;
    User user;
    private ObservableField<User> observableField;

    public FragmentViewProfileDataBinding(Context context) {
        this.context = context;
        observableField = new ObservableField<>();
        user = MyRetrofit.getUser();
        observableField.set(this.user);
    }

    public void onAvatarButtonClicked(View view){
        // TODO 切换头像
    }

    public void onModifyInfoClicked(View view){
        Intent intent = new Intent(context, ActivityPageModifyUserInfo.class);
        context.startActivity(intent);
    }


    public String getDescription(){
        return observableField.get().getDescription();
    }

    public void setDescription(String description){
        observableField.get().setDescription(description);
    }

    public String getUserName(){
        return observableField.get().getUserName();
    }

    public void setUserName(String userName){
        observableField.get().setUserName(userName);
    }

    public String getPassword(){
        return observableField.get().getPassword();
    }

    public void setPassword(String password){
        observableField.get().setPassword(password);
    }

    public String getSex(){
        return observableField.get().getSex();
    }

    public void setSex(String sex){
        observableField.get().setSex(sex);
    }

    public String getPhone(){
        return observableField.get().getPhone();
    }

    public void setPhone(String phone){
        observableField.get().setPhone(phone);
    }

    public String getCreateTime(){
        return observableField.get().getCreateTime();
    }

    public void setCreateTime(String date){
        observableField.get().setCreateTime(date);
    }

    public String getNickname(){
        return observableField.get().getNickName();
    }

    public void setNickname(String nickname){
        observableField.get().setNickName(nickname);
    }

}
