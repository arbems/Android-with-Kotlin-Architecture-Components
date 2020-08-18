package com.arbems.databindingviewmodelandobserver

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.databinding.*
import com.arbems.databindingviewmodelandobserver.data.Enemy

class MainViewModel : ViewModel() {

    val heroName = ObservableField<String>()
    val age = ObservableInt()
    val woman = ObservableBoolean()
    val enemy: Enemy = Enemy()
    // val powers = ObservableArrayList<Any>()

    init {
        heroName.set("Superman")
        age.set(40)
        woman.set(false)
        enemy.apply {
            name = "Alexander Luthor"
            woman = false
        }
    }

    fun setHero(
        heroName: String,
        age: Int,
        woman: Boolean,
        enemyName: String,
        enemyWoman: Boolean
    ) {
        this.heroName.set(heroName)
        this.age.set(age)
        this.woman.set(woman)
        enemy.name = enemyName
        enemy.woman = enemyWoman
    }

    fun reset(view: View) {
        heroName.set("Superman")
        age.set(40)
        woman.set(false)
        enemy.name = "Alexander Luthor"
        enemy.woman = false
    }
}