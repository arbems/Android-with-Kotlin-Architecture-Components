package com.arbems.bindingadapters.util

import android.graphics.drawable.ColorDrawable
import androidx.databinding.BindingConversion

object BindingConverters {
    @BindingConversion
    @JvmStatic fun convertColorToDrawable(color: Int) = ColorDrawable(color)
}