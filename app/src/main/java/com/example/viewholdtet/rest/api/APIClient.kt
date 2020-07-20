package com.example.viewholdtet.rest.api

import com.example.viewholdtet.model.LoginModel
import com.example.viewholdtet.rest.responses.CreateTokenResponse
import com.example.viewholdtet.rest.responses.TrendingResponse
import com.example.viewholdtet.utils.ViewTesterConstants
import retrofit2.Call

class APIClient {
    companion object {
        private var mMovieDBApi: APIClientService =
            APIRestFactory.apiInstance(
                WebApiRequestInterceptor()
            )

        fun createUserToken(): Call<CreateTokenResponse> {
            return mMovieDBApi.generateNewSessionToken(ViewTesterConstants.API_KEY)
        }

        fun generateSessionWithCredentials(loginModel: LoginModel) : Call<CreateTokenResponse> {
            return mMovieDBApi.createSessionValidatingWithLogin(ViewTesterConstants.API_KEY, loginModel)
        }

        fun getTrendingMoviesOfTheWeek() : Call<TrendingResponse> {
            return mMovieDBApi.getTrendingMoviesForWeek("movie", "week", ViewTesterConstants.API_KEY)
        }
    }
}