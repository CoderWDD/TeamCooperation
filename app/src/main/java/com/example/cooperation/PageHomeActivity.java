package com.example.cooperation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.cooperation.constant.ActivityRequestCodeConstant;
import com.example.cooperation.constant.SharedPreferencesConstant;
import com.example.cooperation.utils.AlbumUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;

public class PageHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去除系统状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_home);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        NavigationUI.setupWithNavController(bottomNavigationView,navController);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ActivityRequestCodeConstant.ALBUM_CHOOSE && data != null){
            String path;

            Uri uri = data.getData();

            AlbumUtil albumUtil = new AlbumUtil(this);

            path = albumUtil.getPath(uri);

            Log.i("myTag", "onActivityResult: " + path);

            // 用返回的图片路径读取图片，放入ShapeableImageView，并上传
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            ShapeableImageView avatarImageView = findViewById(R.id.profile_image_avatar);
            avatarImageView.setImageBitmap(bitmap);

//            byte[] avatarBytes = AlbumUtil.bitMapToBytes(bitmap);

//            MyRetrofit.InitInstance();
//            RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();
//
//            Call<ResponseBody> responseBodyCall = retrofitRequestInterface.userAvatarUpload(MyRetrofit.getToken(), avatarBytes);
//
//            responseBodyCall.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    if (response.isSuccessful() && response.body() != null && HttpStatus.OK.equals(response.body().getCode())){
//                        Log.i("myTag", "onResponse: " + response.body().getMessage());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                }
//            });


            // 将图片的路径保持到本地
            SharedPreferences sharedPreferences = getSharedPreferences(SharedPreferencesConstant.SP_NAME, Context.MODE_PRIVATE);

            SharedPreferences.Editor edit = sharedPreferences.edit();

            edit.putString("avatar",path);

            edit.apply();
        }
    }
}