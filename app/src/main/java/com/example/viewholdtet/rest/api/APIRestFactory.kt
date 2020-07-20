package com.example.viewholdtet.rest.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIRestFactory {
    companion object {
        var url: String = "https://api.themoviedb.org/3/"

        fun apiInstance(headerInterceptor: WebApiRequestInterceptor): APIClientService {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(httpLoggingInterceptor)

            builder.addInterceptor(headerInterceptor)
            builder.readTimeout(120, TimeUnit.SECONDS)
            builder.connectTimeout(120, TimeUnit.SECONDS)
            builder.writeTimeout(120, TimeUnit.SECONDS)

            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().setLenient().create()
                    )
                )
                .client(builder.build())
                .build()

            return retrofit.create(APIClientService::class.java!!)
        }
    }
}