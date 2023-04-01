package com.example.qihangcooperation.networks

import com.example.qihangcooperation.application.CooperationApplication
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        // 如果是登录或注册接口，就不添加header
        if (request.url.encodedPath.contains("/api/authenticate", true) || request.url.encodedPath.contains("/api/register", true)) {
            return chain.proceed(request)
        }
        // 添加header
        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer ${CooperationApplication.getGlobalToken()}")
            .build()
        return chain.proceed(newRequest)
    }

}
