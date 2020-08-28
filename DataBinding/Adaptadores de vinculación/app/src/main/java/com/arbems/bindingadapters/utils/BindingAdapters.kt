package com.arbems.bindingadapters.utils

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.squareup.picasso.Picasso

/**
 * Proporciona lógica personalizada
 */
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

@BindingAdapter("currency")
fun bindCurrency(view: TextView, amount: Float) {
    view.text = "$ $amount"
}

/**
 * Especifica un nombre de método personalizado
 */
@BindingMethods(
    value = [
        BindingMethod(
            type = android.widget.ImageView::class,
            attribute = "android:tint",
            method = "setImageTintList"
        )]
)
class ImageViewBindingAdapters


/**
 * Conversiones personalizadas
 * */
@BindingConversion
fun convertColorToDrawable(color: Int) = ColorDrawable(color)