package com.arbems.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arbems.viewbinding.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater) // Esto crea una instancia de la clase de vinculación para la actividad que se usará.
        val view = binding.root // obtiene una referencia a la vista raíz.
        setContentView(view) // Pasa la vista raíz a setContentView() para que sea la vista activa en la pantalla.

        view.name.text = "Alberto Moreno"

        binding.button.setOnClickListener {
            view.name.text = "Carlos Garcia"
        }
    }
}