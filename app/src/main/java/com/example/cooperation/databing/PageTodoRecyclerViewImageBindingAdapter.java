package com.example.cooperation.databing;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.cooperation.R;

public class PageTodoRecyclerViewImageBindingAdapter {
    @BindingAdapter("itemImage")
    public static void setImage(ImageView imageView,String imageUrl){
        if (TextUtils.isEmpty(imageUrl)){
            imageView.setBackgroundResource(R.mipmap.image_todo_recyclerview_item_background);
        }else {
//            Picasso
//                    .get()
//                    .load(imageUrl)
//                    .placeholder(R.mipmap.image_todo_recyclerview_item_background)
//                    .error(R.mipmap.image_todo_recyclerview_item_background)
//                    .into(imageView);
        }
    }
}
