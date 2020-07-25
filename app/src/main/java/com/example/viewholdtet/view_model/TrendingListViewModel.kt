package com.example.viewholdtet.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.viewholdtet.model.AppUser
import com.example.viewholdtet.model.Movie
import com.example.viewholdtet.rest.repo.Repository
import kotlinx.coroutines.launch

class TrendingListViewModel : BaseViewModel() {
    val repoListLive = MutableLiveData<ArrayList<Movie>>()
    val movieSelected = MutableLiveData<Movie>()

    fun requestWeeklyTrendingMovies() {
        mProgress.value = true

        viewModelScope.launch {
            Repository.getInstance().getWeeklyTrendingList { isSuccess, results ->
                mProgress.value = false
                if (isSuccess && !results.isNullOrEmpty()) {
                    repoListLive.value = results
                }
            }
        }
    }
}