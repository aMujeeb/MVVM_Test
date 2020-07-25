package com.example.viewholdtet.rest.api

import com.example.viewholdtet.model.LoginModel
import com.example.viewholdtet.rest.responses.CreateTokenResponse
import com.example.viewholdtet.rest.responses.TrendingResponse
import com.example.viewholdtet.utils.ViewTesterConstants
import retrofit2.Call
import retrofit2.Response

class APIClient {
    companion object {
        private var mMovieDBApi: APIClientService =
            APIRestFactory.apiInstance(
                WebApiRequestInterceptor()
            )

        suspend fun createUserToken(): Response<CreateTokenResponse> {
            return mMovieDBApi.generateNewSessionToken(ViewTesterConstants.API_KEY)
        }

        suspend fun generateSessionWithCredentials(loginModel: LoginModel): Response<CreateTokenResponse> {
            return mMovieDBApi.createSessionValidatingWithLogin(
                ViewTesterConstants.API_KEY,
                loginModel
            )
        }

        suspend fun getTrendingMoviesOfTheWeek(): Response<TrendingResponse> {
            return mMovieDBApi.getTrendingMoviesForWeek(
                "movie",
                "week",
                ViewTesterConstants.API_KEY
            )
        }
    }
}