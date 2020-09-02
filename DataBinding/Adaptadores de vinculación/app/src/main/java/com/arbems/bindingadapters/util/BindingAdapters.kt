package com.arbems.bindingadapters.util

import android.graphics.drawable.Drawable
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.squareup.picasso.Picasso

object BindingAdapters {
    /**
     * Un adaptador de enlace que se llama siempre que cambia el valor del atributo `android:text`.
     * @param value Int
     */
    @BindingAdapter("android:text")
    @JvmStatic fun setText(view: EditText, oldValue: Int, value: Int) {
        if (oldValue != value && value != 0)
            view.setText(value.toString())
    }
    @InverseBindingAdapter(attribute = "android:text")
    @JvmStatic fun getText(view: EditText): Int {
        return if (view.text.toString() == "") 0 else view.text.toString().toInt()
    }

    /**
     * Un adaptador de enlace que se llama siempre que cambia el valor del atributo `android:text`.
     * @param value Double
     */
    @BindingAdapter("android:text")
    @JvmStatic fun setText(view: EditText, value: Double) {
        if(view.text.toString() == "")
            return
        val oldValue = view.text.toString().toDouble()
        if (oldValue != value && value != 0.0)
            view.setText(value.toString())
    }

    @InverseBindingAdapter(attribute = "android:text")
    @JvmStatic fun getTextA(view: EditText): Double {
        return if(view.text.toString() == "") 0.0 else view.text.toString().toDouble()
    }
}


/**
 * Load images
 */
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

/**
 * Currency
 */
@BindingAdapter("currency")
fun bindCurrency(view: EditText, value: Float) {
    if(view.text.toString() == "")
        return
    val oldValue = view.text.toString().toFloat()
    if (oldValue != value && value != 0.0F)
        view.setText(value.toString())
}

@InverseBindingAdapter(attribute = "currency")
fun bindCurrency(view: EditText): Float {
    return if(view.text.toString() == "") 0.0F else view.text.toString().toFloat()
}
