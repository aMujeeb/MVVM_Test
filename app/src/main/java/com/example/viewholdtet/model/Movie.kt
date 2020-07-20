package com.example.viewholdtet.model

data class Movie(var id : Int?) {
    //var id: Int? = null
    var video: Boolean = false
    var vote_count: Int? = null
    var vote_average: Float? = null
    var title: String? = null
    var release_date: String? = null
    var original_language: String? = null
    var original_title: String? = null
    var backdrop_path: String? = null
    var adult: Boolean = false
    var overview: String? = null
    var poster_path: String? = null
    var popularity: Double? = null
    var media_type: String? = null

   /* @BindingAdapter("imageUrl")
    open fun loadImage(imageView : ImageView, url : String?){
        ImageUtils.downloadImageWithoutCache(imageView.context, ViewTesterConstants.SECURE_IMAGE_URL_BACK_DROP + url, imageView)
    }*/
}