package com.example.observables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.*
import com.example.observables.data.Movie
import com.example.observables.databinding.ActivityObservableFieldBinding

class ObservableFieldActivity : AppCompatActivity() {

    private val movie = Movie(
        ObservableField<String>("El bueno, el feo y el malo"),
        ObservableField<String>("EEUU"),
        ObservableArrayMap<String, Any>().apply {
            put("es", "Español")
            put("en", "Inglés")
        }, ObservableArrayList<Any>().apply {
            add("Clint Eastwood")
            add("Eli Wallach")
            add("Lee Van Cleef")
        }, ObservableInt(1960),
        ObservableBoolean(false),
        ObservableInt(0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityObservableFieldBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_observable_field)

        binding.movie = movie
    }

    fun changeMovie(view: View) {
        movie.title.set("Tesis")
        movie.country.set("España")
        movie.languages["es"] = "Esp"
        movie.actors[0] = "Eduardo Noriega"
        movie.year.set(1996)
        movie.blackAndWithe.set(false)
        movie.likes.set(0)
    }
    fun like(view: View) {
        movie.likes.set(movie.likes.get() + 1)
    }

}
