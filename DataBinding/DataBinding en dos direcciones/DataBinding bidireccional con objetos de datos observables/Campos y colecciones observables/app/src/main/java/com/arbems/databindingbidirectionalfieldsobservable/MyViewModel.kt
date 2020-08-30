package com.arbems.databindingbidirectionalfieldsobservable

import androidx.databinding.ObservableDouble
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

/**
 *  ObservableBoolean, ObservableByte, ObservableChar, ObservableShort, ObservableInt, ObservableLong, ObservableFloat, ObservableDouble, ObservableParcelable
 */

class MyViewModel : ViewModel() {
    val editTextContent = ObservableField<String>()

    /**
     * It can also create a calculated field, depending on other fields
     * */
    val temperatureC = ObservableDouble()
    val temperatureF: ObservableDouble = object : ObservableDouble(temperatureC) {
        override fun get(): Double {
            return temperatureC.get() * 9 / 5 + 32
        }
    }
}