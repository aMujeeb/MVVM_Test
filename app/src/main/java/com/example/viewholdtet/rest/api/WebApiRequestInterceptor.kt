package com.example.viewholdtet.rest.api

import com.example.viewholdtet.utils.ViewTesterConstants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class WebApiRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request
        val builder = chain.request().newBuilder()
        request = builder.build()
        builder.addHeader(
            ViewTesterConstants.AUTH_TOKEN_CONTENT_TYPE,
            ViewTesterConstants.AUTH_TOKEN_CONTENT_TYPE_VALUE_JSON
        )
        builder.addHeader(
            ViewTesterConstants.AUTH_CONNECTION_TYPE,
            ViewTesterConstants.AUTH_CONNECTION_TYPE_VALUE
        )
        return chain.proceed(request)
    }
}