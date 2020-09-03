# Android con Kotlin - ViewModels - Factory

*Este ejemplo muestra las siguientes características de [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)*:

* ViewModel con múltiples parámetros usando interfaz Factory

# Documentación

Las implementaciones de la interfaz [Factory](https://developer.android.com/reference/androidx/lifecycle/ViewModelProvider.Factory) son responsables de instanciar ViewModels.
                                                                                                  
No podemos crear [ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel) por nuestra cuenta. Necesitamos la utilidad [ViewModelProvider](https://developer.android.com/reference/androidx/lifecycle/ViewModelProvider) proporcionada por Android para crear ViewModels.

Pero ViewModelProvider solo puede crear instancias de ViewModels **sin parámetros de constructor**.

Entonces si tenemos un ViewModel con múltiples parámetros, necesitamos usar un **ViewModelProvider.Factory** para pasar a ViewModelProvider y obtener una instancia de ViewModel.

`Nota: Podemos simplificar el código usando una clase base de ViewModelFactory. Y podemos simplificar aun más usando ViewModelutils.`


## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
