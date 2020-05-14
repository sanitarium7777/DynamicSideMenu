package com.github.sanitarium7777.utils

import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object ImageBindingAdapter {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun bindImage(view: ImageView, imageUrl: String) {
        Glide.with(view.context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(view)
    }
}

object UrlBindingAdapter {
    @BindingAdapter("webViewUrl")
    @JvmStatic
    fun bindUrl(view: WebView, url: String) {
        view.loadUrl(url)
    }
}
