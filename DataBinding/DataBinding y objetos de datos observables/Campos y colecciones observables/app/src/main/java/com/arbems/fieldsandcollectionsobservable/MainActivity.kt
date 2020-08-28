package com.arbems.fieldsandcollectionsobservable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.*
import com.arbems.fieldsandcollectionsobservable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val user = User(
            ObservableField<String>("Alberto"),
            ObservableInt(37),
            ObservableArrayMap<String, Any>().apply {
                put("es", "Español")
                put("en", "Inglés")
            },
            ObservableArrayList<Any>().apply {
                add("Clint Eastwood")
                add("Eli Wallach")
                add("Lee Van Cleef")
            })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.user = user
    }

    fun changeUser(view: View) {
        user.firstName.set("Carlos")
        user.age.set(28)
        user.languages["es"] = "Spanish"
        user.actors[0] = "Brad Pitt"
    }
}