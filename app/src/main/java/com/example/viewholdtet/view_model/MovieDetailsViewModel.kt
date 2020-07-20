package com.example.viewholdtet.view_model

import androidx.lifecycle.MutableLiveData
import com.example.viewholdtet.model.Movie
import com.google.gson.Gson

class MovieDetailsViewModel : BaseViewModel() {
    private var mMovie: Movie? = null
    val mMovieObject = MutableLiveData<Movie>()

    fun init(movie: String?) {
        mMovie = Gson().fromJson(movie, Movie::class.java)
        mMovieObject.value = mMovie
    }
}