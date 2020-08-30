package com.arbems.databindingviewmodelobservable.data

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.arbems.databindingviewmodelobservable.BR

class EnemyObservable : BaseObservable() {

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