package com.example.cooperation.api;

import com.example.cooperation.constant.HttpStatus;
import com.example.cooperation.model.User;
import com.example.cooperation.model.UserInfoResponseBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {

    //
    private static String baseUrl = "http://106.15.2.32:3345/";

    private static Retrofit retrofit;

    private static String globalToken;

    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        MyRetrofit.user = user;
    }

    public static String getToken() {
        return globalToken;
    }

    public static void setToken(String token) {
        globalToken = token;
    }

    public static Retrofit InitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public static RetrofitRequest_Interface getRetrofitRequestInterface(){
        if (retrofit == null){
            retrofit = InitInstance();
        }
        return retrofit.create(RetrofitRequest_Interface.class);
    }

    public static void RefreshUserInfo(){

        RetrofitRequest_Interface retrofitRequestInterface = getRetrofitRequestInterface();

        Call<UserInfoResponseBody> responseBodyCall = retrofitRequestInterface.userGetCurrent(MyRetrofit.getToken());

        responseBodyCall.enqueue(new Callback<UserInfoResponseBody>() {
            @Override
            public void onResponse(Call<UserInfoResponseBody> call, Response<UserInfoResponseBody> response) {
                if (response.isSuccessful() && response.body() != null && HttpStatus.OK.equals(response.body().getCode())){
                    User temp = response.body().getData();
                    setUser(temp);
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponseBody> call, Throwable t) {

            }
        });

    }
}
