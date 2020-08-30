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

    @Bindable
    var temperatureC: String = ""
        set(value) {
            // Avoids infinite loops.
            if (temperatureC != value) {
                field = value

                // React to the change.
                setTemperatureF()

                // Notify observers of a new value.
                notifyPropertyChanged(BR.temperatureC)
            }
        }

    @Bindable
    var temperatureF: String = ""
        set(value) {
            // Avoids infinite loops.
            if (temperatureF != value) {
                field = value

                // Notify observers of a new value.
                notifyPropertyChanged(BR.temperatureF)
            }
        }


    private fun saveData() {
        // ...
    }

    private fun setTemperatureF() {
        temperatureF = ((temperatureC.toDouble() * 9 / 5) + 32).toString()
    }
}