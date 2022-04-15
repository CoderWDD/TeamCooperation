package com.example.cooperation.api;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {

    //
    private static String baseUrl = "http://106.15.2.32:3345/";

    private static Retrofit retrofit;

    private static String globalToken;

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

}
