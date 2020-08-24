# Android con Kotlin - Ciclo de vida de un ViewModel 

*Código de ejemplo de una aplicación simple que muestra ciclos de vida de un ViewModel en Android con Kotlin.*
* Muestra como un ViewModel resiste a los cambios de configuración.
* Muestra como un ViewModel existe a partir de la primera solicitud hasta que la actividad se termina y se destruye.
  
<br/>
                                                                                                 
El alcance de los objetos [**ViewModel**](https://developer.android.com/reference/androidx/lifecycle/ViewModel) se determina según el [**Lifecycle**](https://developer.android.com/reference/androidx/lifecycle/Lifecycle) transferido al [**ViewModelProvider**](https://developer.android.com/reference/androidx/lifecycle/ViewModelProvider) cuando recibe el ViewModel.
El ViewModel permanece en la memoria hasta que el Lifecycle que determina su alcance desaparece permanentemente. En el caso de una actividad, cuando termina; en el caso de un fragmento, cuando es separado.

Por lo general, solicitas un ViewModel la primera vez que el sistema llama al método onCreate() del objeto de una actividad. El sistema puede llamar a onCreate() varias veces durante la vida de una actividad, como cuando rota la pantalla de un dispositivo. El ViewModel existe desde la primera vez que solicitas un ViewModel hasta que finaliza la actividad y se destruye.

Los objetos ViewModel se retienen automáticamente durante los cambios de configuración, de manera que los datos que conservan están disponibles de inmediato para la siguiente instancia de actividad.

![Lifecycle Viewmodel](https://github.com/arbems/Android-with-Kotlin-Architecture-Components/blob/master/ViewModel/Ciclo%20de%20vida%20de%20un%20ViewModel/0001.png?raw=true)

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
