package com.arbems.databindingviewmodelobservabletwoway

import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter

@BindingAdapter("android:text")
fun setText(view: EditText, value: Int) {
    if(view.text.toString() != value.toString() && value != 0)
        view.setText(value.toString())
}

@InverseBindingAdapter(attribute = "android:text")
fun getText(view: EditText): Int {
    return if(view.text.toString() == "") 0 else view.text.toString().toInt()
}


/*object Converter {
    @InverseMethod("stringToInt")
    @JvmStatic fun intToString(
        view: EditText, oldValue: Int,
        value: Int
    ): String {
        // Converts long to String.
        return value.toString()
    }

    @JvmStatic fun stringToInt(
        view: EditText, oldValue: String,
        value: String
    ): Int {
        // Converts String to long.
        return value.toInt()
    }
}*/