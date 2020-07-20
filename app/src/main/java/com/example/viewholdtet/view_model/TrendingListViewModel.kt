package com.example.viewholdtet.view_model

import androidx.lifecycle.MutableLiveData
import com.example.viewholdtet.model.AppUser
import com.example.viewholdtet.model.Movie
import com.example.viewholdtet.rest.repo.Repository

class TrendingListViewModel : BaseViewModel() {
    val repoListLive = MutableLiveData<ArrayList<Movie>>()
    val movieSelected = MutableLiveData<Movie>()

    fun requestWeeklyTrendingMovies() {
        mProgress.value = true
        Repository.getInstance().getWeeklyTrendingList { isSuccess, results ->
            mProgress.value = false
            if (isSuccess) {
                repoListLive.value = results
            }
        }
    }
}