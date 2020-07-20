package com.example.viewholdtet.views.fragment.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewholdtet.R
import com.example.viewholdtet.databinding.FragmentTrendingListBinding
import com.example.viewholdtet.model.Movie
import com.example.viewholdtet.utils.LoggerUtils
import com.example.viewholdtet.view_model.TrendingListViewModel
import com.example.viewholdtet.views.adapters.TrendingMovieListAdapter
import com.example.viewholdtet.views.fragment.BaseFragment
import com.google.gson.Gson

class TrendingListFragment : BaseFragment() {

    private lateinit var trendingListViewModel: TrendingListViewModel
    private lateinit var fragmentTrendingListBinding: FragmentTrendingListBinding
    private lateinit var mMovieListAdapter : TrendingMovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_trending_list, container, false)
        fragmentTrendingListBinding = FragmentTrendingListBinding.inflate(inflater, container, false).apply {
            trendingListViewModel = ViewModelProvider(this@TrendingListFragment).get(TrendingListViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return fragmentTrendingListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObservers()

        trendingListViewModel.requestWeeklyTrendingMovies()
    }

    private fun setupObservers() {
        trendingListViewModel.mProgress.observe(viewLifecycleOwner, Observer<Boolean>{ t->
            if(t){
                showProgress()
            } else {
                hideProgress()
            }
        })

        trendingListViewModel.repoListLive.observe(viewLifecycleOwner, Observer<ArrayList<Movie>>{ t ->
            mMovieListAdapter.setMovies(t)
        })

        trendingListViewModel.movieSelected.observe(viewLifecycleOwner, Observer<Movie>{ t ->
            LoggerUtils.logMessage("Movie Clicked")
        })
    }

    private fun setupAdapter() {
        mMovieListAdapter = TrendingMovieListAdapter(trendingListViewModel, requireActivity())
        var mMovieList = fragmentTrendingListBinding.mLstTopItems
        mMovieList.layoutManager = LinearLayoutManager(context)
        mMovieList.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager(context).orientation))
        mMovieList.adapter = mMovieListAdapter
    }
}