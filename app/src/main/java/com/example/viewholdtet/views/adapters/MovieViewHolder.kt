package com.example.viewholdtet.views.adapters

import android.app.Activity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.viewholdtet.R
import com.example.viewholdtet.databinding.ItemMovieBinding
import com.example.viewholdtet.utils.ImageUtils
import com.example.viewholdtet.utils.ViewTesterConstants.Companion.SECURE_IMAGE_URL_THUMBNAIL
import com.example.viewholdtet.view_model.TrendingListViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(var dataBinding : ItemMovieBinding, var mActivity : Activity) : RecyclerView.ViewHolder(dataBinding.root) {
    fun setUpData() {
        val url = SECURE_IMAGE_URL_THUMBNAIL + dataBinding.mMovie!!.poster_path
        ImageUtils.downloadImageWithoutCache(mActivity, url, itemView.mImgMovieImage)

        itemView.setOnClickListener {
            val bundle = bundleOf("selected_movie" to Gson().toJson(dataBinding.mMovie!!))
            itemView.findNavController().navigate(R.id.action_topTenListFragment_to_movieDetailsFragment, bundle)
        }
    }
}