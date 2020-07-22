package com.example.viewholdtet.views.fragment.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewholdtet.R
import com.example.viewholdtet.databinding.FragmentDetailsBinding
import com.example.viewholdtet.utils.ImageUtils
import com.example.viewholdtet.utils.ViewTesterConstants
import com.example.viewholdtet.view_model.MovieDetailsViewModel
import com.example.viewholdtet.views.fragment.BaseFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MovieDetailsFragment : BaseFragment() {

    private lateinit var mMovieDetailsViewModel : MovieDetailsViewModel
    private lateinit var mFragmentDetailsBinding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mFragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false).apply {
            mMovieDetailsViewModel = ViewModelProvider(this@MovieDetailsFragment).get(MovieDetailsViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return mFragmentDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMovieDetailsViewModel.init(MovieDetailsFragmentArgs.fromBundle(requireArguments()).selectedMovie)
        setupObservers()
    }

    private fun setupObservers() {
        mMovieDetailsViewModel.mMovieObject.observe(viewLifecycleOwner, Observer {
            mFragmentDetailsBinding.mChosenMovie = it
            ImageUtils.downloadImageWithoutCache(requireActivity(), ViewTesterConstants.SECURE_IMAGE_URL_BACK_DROP + it.backdrop_path, mFragmentDetailsBinding.mImgLargeBanner)
        })
    }
}