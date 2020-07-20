package com.example.viewholdtet.rest.api

import com.example.viewholdtet.model.LoginModel
import com.example.viewholdtet.rest.responses.CreateTokenResponse
import com.example.viewholdtet.rest.responses.TrendingResponse
import retrofit2.Call
import retrofit2.http.*

interface APIClientService {

    @GET("authentication/token/new")
    fun generateNewSessionToken(
        @Query("api_key") apiKey: String
    ): Call<CreateTokenResponse>

    @POST("authentication/token/validate_with_login")
    fun createSessionValidatingWithLogin(
        @Query("api_key") apiKey: String,
        @Body login: LoginModel
    ) : Call<CreateTokenResponse>

    @GET("trending/{media_type}/{time_window}")
    fun getTrendingMoviesForWeek(
        @Path("media_type") media : String,
        @Path("time_window") window : String,
        @Query("api_key") apiKey: String
    ) : Call<TrendingResponse>
}