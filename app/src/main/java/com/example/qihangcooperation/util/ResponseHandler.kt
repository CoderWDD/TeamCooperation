package com.example.qihangcooperation.util

import android.content.Context
import android.util.Log
import android.widget.Toast

object ResponseHandler {
    fun handleError(error: String, context: Context){
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        Log.e("onError", "handleError: $error")
    }

    fun handleSuccess(msg: String, context: Context){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        Log.d("onSuccess", "handleSuccess: $msg")
    }
}