package com.arbems.viewmodelbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    /**
     * Declara ViewModel
     */
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModel2: AndroidViewModel

    /**
     * Obtener ViewModel usando activity-ktx artifact
     * */
    private val viewModel3 by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Obtiene una instancia de ViewModel existente o crea uno nuevo
         *
         * Asigna un alcance a un ViewModel mediante ViewModelStoreOwner
         */
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // or
        viewModel2 = ViewModelProvider(this)[AndroidViewModel::class.java]



        /**
         * Recuperar un ViewModelStore de la actividad
         * */
        viewModelStore.apply {
            viewModel
            viewModel2
            viewModel3
            savedInstanceState
            //...
        }
    }
}