package com.example.cooperation.databing;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cooperation.R;
import com.example.cooperation.api.MyRetrofit;
import com.example.cooperation.api.RetrofitRequest_Interface;
import com.example.cooperation.constant.HttpStatus;
import com.example.cooperation.model.ItemAdd;
import com.example.cooperation.model.ResponseBody;
import com.example.cooperation.utils.SpinnerPriorityUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageItemDetailsDataBinding {
    private Context context;

    public PageItemDetailsDataBinding(Context context) {
        this.context = context;
    }

    public void onModifyItemClick(View view, ItemAdd itemAdd){
        Spinner spinnerStatus = ((Activity) context).findViewById(R.id.spinner_item_status);
        String selectStatusItem = spinnerStatus.getSelectedItem().toString();
        itemAdd.setStatus(selectStatusItem);

        Spinner spinnerPriority = ((Activity) context).findViewById(R.id.spinner_item_details_priority);

        String spinnerPriorityItem = spinnerPriority.getSelectedItem().toString();
        int spinnerPriorityIndex = SpinnerPriorityUtil.SelectItemToIndex(spinnerPriorityItem);
        itemAdd.setPriority(spinnerPriorityIndex);

        MyRetrofit.InitInstance();
        RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();

        Call<ResponseBody> responseBodyCall = retrofitRequestInterface.itemModifyInfo(MyRetrofit.getToken(), itemAdd.getItemId(), itemAdd);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null && HttpStatus.OK.equals(response.body().getCode())){
                    Toast.makeText(context,response.body().getData(),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"Something Wrong!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
