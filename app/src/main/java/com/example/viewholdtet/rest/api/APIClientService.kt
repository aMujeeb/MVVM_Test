package com.example.viewholdtet.rest.api

import com.example.viewholdtet.model.LoginModel
import com.example.viewholdtet.rest.responses.CreateTokenResponse
import com.example.viewholdtet.rest.responses.TrendingResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIClientService {

    @GET("authentication/token/new")
    suspend fun generateNewSessionToken(
        @Query("api_key") apiKey: String
    ): Response<CreateTokenResponse>

    @POST("authentication/token/validate_with_login")
    suspend fun createSessionValidatingWithLogin(
        @Query("api_key") apiKey: String,
        @Body login: LoginModel
    ): Response<CreateTokenResponse>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingMoviesForWeek(
        @Path("media_type") media: String,
        @Path("time_window") window: String,
        @Query("api_key") apiKey: String
    ): Response<TrendingResponse>
}