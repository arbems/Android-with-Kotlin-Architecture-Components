package com.arbems.viewmodelbasic

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class AndroidViewModel(application: Application): AndroidViewModel(application) {
    private val context = application.applicationContext
}