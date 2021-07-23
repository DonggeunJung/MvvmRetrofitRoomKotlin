package com.example.mvvmretrofitroomkt

import android.app.Application
import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        context = this
    }

    companion object {
        lateinit var context: Context

        // DataBinding - ImageView:imageUrl
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, imageUrl: String?) {
            imageUrl?.let {
                Glide.with(view.context).load(it)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(view)
            }
        }
    }
}