package com.arbems.databindingviewmodelobservabletwoway

import android.widget.TextView
import androidx.databinding.*
import androidx.lifecycle.ViewModel
import com.arbems.databindingviewmodelobservabletwoway.data.User
import java.time.LocalDate


class MainViewModel : ViewModel() {

    val user: User = User.build(
        "",
        "",
        "",
        0,
        false
    )
}