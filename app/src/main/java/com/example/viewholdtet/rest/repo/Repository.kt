package com.example.viewholdtet.rest.repo

import com.example.viewholdtet.model.Movie
import com.example.viewholdtet.rest.api.APIClient
import com.example.viewholdtet.rest.responses.TrendingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    companion object {
        private var INSTANCE: Repository? = null
        fun getInstance() = INSTANCE
            ?: Repository().also {
                INSTANCE = it
            }
    }

    suspend fun getWeeklyTrendingList(onResult: (isSuccess: Boolean, results: ArrayList<Movie>?) -> Unit) {
        val response = APIClient.getTrendingMoviesOfTheWeek()
        onResult(response.isSuccessful, response.body()?.results)
    }
}