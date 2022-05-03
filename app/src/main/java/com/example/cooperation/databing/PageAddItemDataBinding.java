package com.example.cooperation.databing;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cooperation.R;
import com.example.cooperation.api.MyRetrofit;
import com.example.cooperation.api.RetrofitRequest_Interface;
import com.example.cooperation.constant.HttpStatus;
import com.example.cooperation.model.ItemAdd;
import com.example.cooperation.model.ResponseBody;
import com.example.cooperation.utils.check.input.AddItemCheckUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageAddItemDataBinding {
    private Context context;

    public PageAddItemDataBinding(Context context) {
        this.context = context;
    }

    public void onAddToProjectClick(View view, ItemAdd item){
        if (!AddItemCheckUtil.check(context,item)) {
            return;
        }
        // 发起网络请求，将item添加到Project中

        Spinner spinnerStatus = (Spinner)((Activity) context).findViewById(R.id.spinner_status);
        Spinner spinnerExecutor = (Spinner)((Activity) context).findViewById(R.id.spinner_executor);
        String status = ((String)spinnerStatus.getSelectedItem());
        String executor = ((String)spinnerExecutor.getSelectedItem());
        item.setStatus(status);
        item.setExecutor(executor);

        MyRetrofit.InitInstance();
        RetrofitRequest_Interface retrofitRequestInterface = MyRetrofit.getRetrofitRequestInterface();

        Call<ResponseBody> responseBodyCall = retrofitRequestInterface.itemAdd(MyRetrofit.getToken(), item);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null && HttpStatus.OK.equals(response.body().getCode())){
                    Toast.makeText(context,"Item add succeed！",Toast.LENGTH_SHORT).show();
                    ((Activity) context).finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

}
