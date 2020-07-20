package com.example.viewholdtet.views.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.viewholdtet.R
import com.example.viewholdtet.databinding.ItemMovieBinding
import com.example.viewholdtet.model.Movie
import com.example.viewholdtet.utils.LoggerUtils
import com.example.viewholdtet.view_model.TrendingListViewModel
import com.google.gson.Gson

class TrendingMovieListAdapter(var mTrendingListViewModel: TrendingListViewModel, var mActivity : Activity) : RecyclerView.Adapter<MovieViewHolder>() {

    private var mMovieList : ArrayList<Movie>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        var mMovieBinding: ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie, parent, false)
        return MovieViewHolder(mMovieBinding, mActivity)
    }

    override fun getItemCount(): Int {
        return if(mMovieList == null) {
            0
        } else {
            mMovieList!!.size
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.dataBinding.mMovie = mMovieList!![position]
        holder.setUpData()
    }

    fun setMovies(studentList: ArrayList<Movie>) {
        this.mMovieList = studentList
        notifyDataSetChanged()
    }
}