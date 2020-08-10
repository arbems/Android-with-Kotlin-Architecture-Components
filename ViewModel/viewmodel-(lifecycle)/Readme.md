# Android con Kotlin - Ciclo de vida de ViewModels 

Código de ejemplo de una aplicación simple que muestra ciclos de vida de un ViewModel en Android con Kotlin.
                                                                                                  
El alcance de los objetos ViewModel se determina según el Lifecycle transferido al ViewModelProvider cuando recibe el ViewModel.
El ViewModel permanece en la memoria hasta que el Lifecycle que determina su alcance desaparece permanentemente. En el caso de una actividad, cuando termina; en el caso de un fragmento, cuando es separado.

![Lifecycle Viewmodel](https://github.com/arbems/Android-with-Kotlin-ViewModels/blob/master/viewmodel-(lifecycle)/0001.png)

Los objetos ViewModel se retienen automáticamente durante los cambios de configuración, de manera que los datos que conservan están disponibles de inmediato para la siguiente instancia de actividad o fragmento.

Solicitas un ViewModel la primera vez que el sistema llama al método onCreate() del objeto de una actividad.
El ViewModel existe a partir de la primera solicitud de un ViewModel hasta que la actividad se termina y se destruye.

Cuando la actividad se termina, el marco de trabajo llama al método onCleared() del objeto ViewModel para que borre los recursos.

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
