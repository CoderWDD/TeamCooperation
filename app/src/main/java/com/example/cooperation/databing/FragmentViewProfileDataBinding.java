package com.example.cooperation.databing;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableField;

import com.example.cooperation.ActivityPageModifyUserInfo;
import com.example.cooperation.LoginPageActivity;
import com.example.cooperation.api.MyRetrofit;
import com.example.cooperation.constant.ActivityRequestCodeConstant;
import com.example.cooperation.constant.SharedPreferencesConstant;
import com.example.cooperation.model.User;
import com.example.cooperation.utils.MyPermissionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentViewProfileDataBinding{
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
//        PackageManager.PERMISSION_GRANTED
        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        boolean permissionCheck = MyPermissionUtil.checkPermissions(context, permissions);
        Log.i("myTag", "onAvatarButtonClicked: " + permissionCheck);
        if (!permissionCheck){
            if (MyPermissionUtil.requestPermission(context,permissions)){
                // 打开相册，读取图片，进行网络请求
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                ((Activity)context).startActivityForResult(intent, ActivityRequestCodeConstant.ALBUM_CHOOSE);
            }
        }else {
            // 打开相册，读取图片，进行网络请求
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            ((Activity)context).startActivityForResult(intent, ActivityRequestCodeConstant.ALBUM_CHOOSE);
        }
    }

    public void onModifyInfoClicked(View view){
        Intent intent = new Intent(context, ActivityPageModifyUserInfo.class);
        context.startActivity(intent);
    }

    public void onLogoutClicked(View view){

        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesConstant.SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();

        edit.remove(SharedPreferencesConstant.USERNAME);
        edit.remove(SharedPreferencesConstant.PASSWORD);
        edit.remove(SharedPreferencesConstant.NICKNAME);
        edit.remove(SharedPreferencesConstant.FIRSTNAME);
        edit.remove(SharedPreferencesConstant.LASTNAME);
        edit.remove(SharedPreferencesConstant.PHONE);
        edit.remove(SharedPreferencesConstant.DEPARTMENT);
        edit.remove(SharedPreferencesConstant.DESCRIPTION);
        edit.remove(SharedPreferencesConstant.CREATE_TIME);
        edit.remove(SharedPreferencesConstant.TOKEN);
        edit.remove(SharedPreferencesConstant.AVATAR);
        edit.remove(SharedPreferencesConstant.FIRST_USE);

        edit.apply();

        Intent intent = new Intent(context, LoginPageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }


    public String getDescription(){
        if ("NULL".equals(Objects.requireNonNull(observableField.get()).getDescription())){
            return "No description";
        }
        return Objects.requireNonNull(observableField.get()).getDescription();
    }

    public void setDescription(String description){
        if ("NULL".equals(Objects.requireNonNull(observableField.get()).getDescription())){
            Objects.requireNonNull(observableField.get()).setDescription("No description");
            return;
        }
        Objects.requireNonNull(observableField.get()).setDescription(description);
    }

    public String getUserName(){
        return Objects.requireNonNull(observableField.get()).getUserName();
    }

    public void setUserName(String userName){
        Objects.requireNonNull(observableField.get()).setUserName(userName);
    }

    public String getPassword(){
        return Objects.requireNonNull(observableField.get()).getPassword();
    }

    public void setPassword(String password){
        Objects.requireNonNull(observableField.get()).setPassword(password);
    }

    public String getSex(){
        return Objects.requireNonNull(observableField.get()).getSex();
    }

    public void setSex(String sex){
        Objects.requireNonNull(observableField.get()).setSex(sex);
    }

    public String getPhone(){
        return Objects.requireNonNull(observableField.get()).getPhone();
    }

    public void setPhone(String phone){
        Objects.requireNonNull(observableField.get()).setPhone(phone);
    }

    public String getCreateTime(){
        return Objects.requireNonNull(observableField.get()).getCreateTime();
    }

    public void setCreateTime(String date){
        Objects.requireNonNull(observableField.get()).setCreateTime(date);
    }

    public String getNickname(){
        return Objects.requireNonNull(observableField.get()).getNickName();
    }

    public void setNickname(String nickname){
        Objects.requireNonNull(observableField.get()).setNickName(nickname);
    }

}
