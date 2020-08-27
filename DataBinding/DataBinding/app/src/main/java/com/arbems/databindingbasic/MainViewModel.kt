package com.arbems.databindingbasic

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _team1Name = MutableLiveData<String>()
    val team1Name: LiveData<String> get() = _team1Name

    private val _team1Score = MutableLiveData<Int>()
    val team1Score: LiveData<Int> get() = _team1Score

    private val _team2Name = MutableLiveData<String>()
    val team2Name: LiveData<String> get() = _team2Name

    private val _team2Score = MutableLiveData<Int>()
    val team2Score: LiveData<Int> get() = _team2Score

    private val _winner = MutableLiveData<String>()
    val winner: LiveData<String> get() = _winner

    init {
        _team1Name.value = "Arsenal"
        _team1Score.value = 0
        _team2Name.value = "Tottenham"
        _team2Score.value = 0
        _winner.value = ""
    }

    fun addScoreTeam1(view: View) {
        _team1Score.value = _team1Score.value?.plus(1)
        _winner.value =
            if (_team1Score.value!!.toInt() > _team2Score.value!!.toInt()) _team1Name.value.toString() else if (_team1Score.value!!.toInt() == _team2Score.value!!.toInt()) "Empate" else _team2Name.value.toString()
    }

    fun addScoreTeam2() {
        _team2Score.value = _team2Score.value?.plus(1)
        _winner.value =
            if (_team1Score.value!!.toInt() > _team2Score.value!!.toInt()) _team1Name.value.toString() else if (_team1Score.value!!.toInt() == _team2Score.value!!.toInt()) "Empate" else _team2Name.value.toString()
    }
}