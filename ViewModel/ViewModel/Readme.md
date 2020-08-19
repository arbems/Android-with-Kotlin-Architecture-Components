# Android con Kotlin - ViewModel

*Código de ejemplo de una aplicación simple con ViewModel en Android con Kotlin.*
                                                                                                  
La clase [**ViewModel**](https://developer.android.com/reference/androidx/lifecycle/ViewModel) está diseñada para almacenar y administrar datos relacionados con la IU de manera optimizada para los ciclos de vida, la clase ViewModel permite que los datos sobrevivan a cambios de configuración.

Se encarga de preparar y administrar los datos para una Activity o un Fragment. También maneja la comunicación de la Activity / Fragment con el resto de la aplicación (por ejemplo, llamando a las clases de lógica de negocios).

### Implementar un ViewModel

Los componentes de arquitectura proporcionan una clase de ayuda de ViewModel para el controlador de IU que es responsable de preparar los datos de la IU.

```kotlin
class MainViewModel: ViewModel() {

    // Properties and variables
    var varName = ""
    private lateinit var list: MutableList<String>

    init {
        // init block is guaranteed to execute only once
        Log.i("ViewModel", "ViewModel created!")
    }

    // Callback called when ViewModel is destroyed
    override fun onCleared() {
        super.onCleared()
        Log.i("ViewModel", "ViewModel destroyed!")
    }

    // Methods for updating the UI
    fun onSkip() { /*...*/ }
    fun onCorrect() { /*...*/ }
}
```

Obtenga una instancia de viewModel existente o cree una nueva.

Devuelve un ViewModel existente o crea uno nuevo en el alcance (generalmente, un fragmento o una actividad), asociado con este ViewModelProvider.

Y permanecerá mientras el alcance esté activo (por ejemplo, si es una actividad, hasta que finalice o el proceso finalice)

`````kotlin
val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
// or using activity-ktx artifact
val viewModel: MainViewModel by viewModels()
`````

`Precaución: Un ViewModel nunca debe hacer referencia a una vista, a un Lifecycle o a una clase que pueda hacer referencia al contexto de la actividad.
 La única responsabilidad de ViewModel es administrar los datos para la IU.`
 
 Los objetos ViewModel pueden contener LifecycleObservers, como objetos LiveData. Sin embargo, los objetos ViewModel no deben observar cambios en los elementos optimizados para ciclos de vida, como los objetos LiveData.
 
 Si ViewModel necesita el contexto de Application, por ejemplo, para buscar un servicio del sistema, puede extender la clase [**AndroidViewModel**](https://developer.android.com/reference/androidx/lifecycle/AndroidViewModel) y lograr que un constructor reciba la Application, ya que la clase Application extiende Context.
 
## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.