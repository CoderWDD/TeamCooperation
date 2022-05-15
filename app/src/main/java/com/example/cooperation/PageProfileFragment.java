package com.example.cooperation;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.cooperation.constant.SharedPreferencesConstant;
import com.example.cooperation.databinding.FragmentPageProfileBinding;
import com.example.cooperation.databing.FragmentViewProfileDataBinding;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Objects;


public class PageProfileFragment extends Fragment {



    public PageProfileFragment() {

    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentPageProfileBinding fragmentPageProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_page_profile, container, false);

        fragmentPageProfileBinding.setProfileEventHandler(new FragmentViewProfileDataBinding(getContext()));

        // 获取本地的头像地址
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(SharedPreferencesConstant.SP_NAME, Context.MODE_PRIVATE);
        String avatarPath = sharedPreferences.getString("avatar", "");
        if (!avatarPath.equals("")){
            ShapeableImageView avatarImageView = fragmentPageProfileBinding.getRoot().findViewById(R.id.profile_image_avatar);
            Bitmap bitmap = BitmapFactory.decodeFile(avatarPath);
            // 如果图片还存在，就用，没有就用默认的
            if (bitmap != null){
                avatarImageView.setImageBitmap(bitmap);
            }
        }

        return fragmentPageProfileBinding.getRoot();
    }

}