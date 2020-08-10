# Android with Kotlin - ViewModels and ViewModelFactory

Código de ejemplo de una aplicación simple de ViewModel y ViewModelFactory en Android con Kotlin.
                                                                                                  
No podemos crear ViewModel por nuestra cuenta. Necesitamos la utilidad ViewModelProviders proporcionada por Android para crear ViewModels.

Pero ViewModelProviders solo puede crear instancias de ViewModels sin constructor arg.

Entonces si tenemos un ViewModel con múltiples argumentos, necesitamos usar un Factory para pasar a ViewModelProviders y obtener una instancia de ViewModel.


## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.