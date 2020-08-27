package com.arbems.bindingadapters.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.squareup.picasso.Picasso

@BindingAdapter("paddingLeft")
fun setPaddingLeft(view: View, padding: Int) {
    view.setPadding(padding,
        view.paddingTop,
        view.paddingRight,
        view.paddingBottom
    )
}

@BindingAdapter("imageUrl", "error")
fun loadImage(view: ImageView, url: String, error: Drawable) {
    Picasso.get().load(url).error(error).into(view)
}

@BindingMethods(
    value = [
        BindingMethod(
            type = ImageView::class,
            attribute = "android:tint",
            method = "setImageTintList",
        ),
    ],
)
