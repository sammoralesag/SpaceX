package com.test.spacex.domain.framework.implementations

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.test.spacex.R
import com.test.spacex.domain.framework.interfaces.IImageLoader
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GlideImageLoader @Inject constructor(@ApplicationContext val appContext: Context): IImageLoader {

    override fun loadImage(view: ImageView, resource: String?) {
        if (resource != null) {
            Glide.with(appContext).load(resource).placeholder(R.drawable.placeholder).into(view)
        } else {
            view.setImageResource(R.drawable.placeholder)
        }
    }
}