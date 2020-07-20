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

    fun getWeeklyTrendingList(onResult: (isSuccess: Boolean, results: ArrayList<Movie>?) -> Unit) {
        APIClient.getTrendingMoviesOfTheWeek().enqueue(object : Callback<TrendingResponse> {
            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                onResult(false, null)
            }

            override fun onResponse(
                call: Call<TrendingResponse>,
                response: Response<TrendingResponse>
            ) {
                if (response.isSuccessful) {
                    onResult(true, response.body()!!.results)
                } else {
                    onResult(false, null)
                }
            }
        })
    }
}