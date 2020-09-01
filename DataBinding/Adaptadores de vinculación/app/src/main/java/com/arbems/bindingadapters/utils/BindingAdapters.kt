package com.arbems.bindingadapters.utils

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.*
import com.squareup.picasso.Picasso


@BindingAdapter("android:text")
fun setText(view: EditText, value: Int) {
    val oldText = view.text.toString();
    if (oldText != value.toString() && value != 0)
        view.setText(value.toString())
}

@InverseBindingAdapter(attribute = "android:text")
fun getText(view: EditText): Int {
    return if (view.text.toString() == "") 0 else view.text.toString().toInt()
}

// -------
@BindingAdapter("android:text")
fun setText(view: EditText, value: Double) {
    if(view.text.toString() == "")
        return
    val oldDouble = view.text.toString().toDouble()
    if (oldDouble != value && value != 0.0)
        view.setText(value.toString())
}

@InverseBindingAdapter(attribute = "android:text")
fun getTextA(view: EditText): Double {
    return if(view.text.toString() == "") 0.0 else view.text.toString().toDouble()
}

// -------

/**
 * Proporciona lógica personalizada
 */
@BindingAdapter("paddingLeft")
fun setPaddingLeft(view: View, padding: Int) {
    view.setPadding(
        padding,
        view.paddingTop,
        view.paddingRight,
        view.paddingBottom
    )
}

@BindingAdapter("android:paddingLeft")
fun setPaddingLeft(view: View, oldPadding: Int, newPadding: Int) {
    if (oldPadding != newPadding) {
        view.setPadding(
            newPadding,
            view.paddingTop,
            view.paddingRight,
            view.paddingBottom
        )
    }
}

@BindingAdapter("imageUrl", "error")
fun loadImage(view: ImageView, url: String, error: Drawable) {
    Picasso.get().load(url).error(error).into(view)
}

@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun setImageUrl(imageView: ImageView, url: String?, placeHolder: Drawable?) {
    if (url == null) {
        imageView.setImageDrawable(placeHolder);
    } else {
        Picasso.get().load(url).into(imageView)
    }
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
        ),
        BindingMethod(
            type = android.widget.ImageView::class,
            attribute = "android:example",
            method = "setExampleX"
        )
        // ...
    ]
)
class ImageViewBindingAdapters


/**
 * Conversiones personalizadas
 * */
@BindingConversion
fun convertColorToDrawable(color: Int) = ColorDrawable(color)