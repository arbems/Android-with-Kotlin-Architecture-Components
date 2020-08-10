# Android con Kotlin - ViewModels - ViewModelFactory

Código de ejemplo de una aplicación simple de ViewModel y ViewModelFactory en Android con Kotlin.
                                                                                                  
No podemos crear ViewModel por nuestra cuenta. Necesitamos la utilidad ViewModelProvider proporcionada por Android para crear ViewModels.

Pero ViewModelProvider solo puede crear instancias de ViewModels sin parámetros de constructor.

Entonces si tenemos un ViewModel con múltiples parámetros, necesitamos usar un Factory para pasar a ViewModelProvider y obtener una instancia de ViewModel.

Nota: Podemos simplificar el código usando una clase base de ViewModelFactory. Y podemos simplificar aun más usando ViewModelutils.


## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
