package com.arbems.databindingbidirectionalobjetsobservable

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import java.io.Serializable

class Object : BaseObservable(), Serializable {

    @Bindable
    var editTextContent: String = ""
        set(value) {
            // Avoids infinite loops.
            if (editTextContent != value) {
                field = value

                // React to the change.
                saveData()

                // Notify observers of a new value.
                notifyPropertyChanged(BR.editTextContent)
            }
        }

    private fun saveData() {
        // ...
    }
}