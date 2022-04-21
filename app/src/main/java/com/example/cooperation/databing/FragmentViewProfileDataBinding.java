package com.example.cooperation.databing;

import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableField;

import com.example.cooperation.model.User;

import java.util.Date;

public class FragmentViewProfileDataBinding {
    private Context context;
    private ObservableField<User> observableField;

    public FragmentViewProfileDataBinding(Context context) {
        this.context = context;
        User user = new User();
        observableField = new ObservableField<>();
        user.setAvatar(null);
        user.setDescription("user description");
        user.setUserName("username");
        user.setPassword("");
        user.setSex(1);
        user.setPhone("110");
        user.setNickName("nickname");
        user.setCreateTime(new Date());
        observableField.set(user);
    }

    public void onAvatarButtonClicked(View view){
        // TODO 切换头像
    }

    public byte[] getAvatar(){
        return observableField.get().getAvatar();
    }

    public void setAvatar(byte[] avatar){
        observableField.get().setAvatar(avatar);
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

    public int getSex(){
        return observableField.get().getSex();
    }

    public void setSex(int sex){
        observableField.get().setSex(sex);
    }

    public String getPhone(){
        return observableField.get().getPhone();
    }

    public void setPhone(String phone){
        observableField.get().setPhone(phone);
    }

    public Date getCreateTime(){
        return observableField.get().getCreateTime();
    }

    public void setCreateTime(Date date){
        observableField.get().setCreateTime(date);
    }

    public String getNickname(){
        return observableField.get().getNickName();
    }

    public void setNickname(String nickname){
        observableField.get().setNickName(nickname);
    }

}
