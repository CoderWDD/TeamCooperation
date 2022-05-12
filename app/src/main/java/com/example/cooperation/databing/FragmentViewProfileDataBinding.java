package com.example.cooperation.databing;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableField;

import com.example.cooperation.ActivityPageModifyUserInfo;
import com.example.cooperation.LoginPageActivity;
import com.example.cooperation.api.MyRetrofit;
import com.example.cooperation.constant.ActivityRequestCodeConstant;
import com.example.cooperation.constant.SharedPreferencesConstant;
import com.example.cooperation.model.User;
import com.example.cooperation.utils.AlbumUtil;
import com.example.cooperation.utils.MyPermissionUtil;

import java.util.ArrayList;
import java.util.List;

public class FragmentViewProfileDataBinding extends AppCompatActivity {
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
        // 切换头像
//        int i = context.checkSelfPermission(PermissionConstant.READ_EXTERNAL_STORAGE);

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
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ActivityRequestCodeConstant.ALBUM_CHOOSE && resultCode == ActivityRequestCodeConstant.OK && data != null){
            String path = null;

            Uri uri = data.getData();

            AlbumUtil albumUtil = new AlbumUtil(context);

            path = albumUtil.getPath(uri);

            Log.i("myTag", "onActivityResult: " + path);

            // TODO 将放回的图片路径读取图片，放进imageButton，然后上传

            Bitmap bitmap = BitmapFactory.decodeFile(path);


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

        edit.commit();


        Intent intent = new Intent(context, LoginPageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }


    public void onTestAvatarClicked(View view){

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
