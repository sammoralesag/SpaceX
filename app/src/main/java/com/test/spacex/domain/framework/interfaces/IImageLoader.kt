package com.test.spacex.domain.framework.interfaces

import android.widget.ImageView

interface IImageLoader {
    fun loadImage(view: ImageView, resource: String? = null)
}