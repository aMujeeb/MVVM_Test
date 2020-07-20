package com.example.viewholdtet.utils

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.viewholdtet.R

class ImageUtils {
    companion object {
        fun downloadImageWithoutCache(context: Context, url: String, imageView: ImageView) {
            Glide.with(context)
                .load(url)
                .apply(RequestOptions.errorOf(R.drawable.ic_top))
                .apply(RequestOptions.skipMemoryCacheOf(false))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
                .into(imageView)
        }

        fun displayImageWithURI(mActivity: Activity, uri: Uri, imageView: ImageView) {
            Glide.with(mActivity.baseContext)
                .load(uri)
                .into(imageView)
        }
    }
}