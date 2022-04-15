package com.example.cooperation.utils;

import com.example.cooperation.model.response.Token;
import com.google.gson.Gson;

public class DecodeJsonFromData {
    public static Token GetToken(String data){
        Gson gson = new Gson();
        return gson.fromJson(data, Token.class);
    }
}
