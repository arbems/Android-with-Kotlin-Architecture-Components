package com.arbems.databindingviewmodelandobserver.data

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.arbems.databindingviewmodelandobserver.BR

class Enemy : BaseObservable() {

    @get:Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var woman: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.woman)
        }
}