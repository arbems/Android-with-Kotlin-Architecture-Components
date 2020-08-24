# Android con Kotlin - ViewModel

*Código de ejemplo de una aplicación simple con ViewModel en Android con Kotlin.*
                                                                                                  
La clase [**ViewModel**](https://developer.android.com/reference/androidx/lifecycle/ViewModel) está diseñada para almacenar y administrar los datos para una Activity o un Fragment de manera optimizada para los ciclos de vida, lo cual permite que los datos sobrevivan a cambios de configuración. También maneja la comunicación de la Activity / Fragment con el resto de la aplicación (por ejemplo, llamando a las clases de lógica de negocios).

## Implementar un ViewModel

Los componentes de arquitectura proporcionan una clase de ayuda llamada ViewModel para el controlador de IU que es responsable de preparar los datos de la IU. Para crear un ViewModel hay que heredar de esta clase:

```kotlin
class MainViewModel: ViewModel() {

    // Properties and variables
    var varName = ""

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

Obtenga una instancia de viewModel existente o cree una nueva:

`````kotlin
val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
// or using activity-ktx artifact
val viewModel: MainViewModel by viewModels()
`````

Devuelve un ViewModel existente o crea uno nuevo en el alcance (generalmente, un fragmento o una actividad), asociado con este ViewModelProvider.

Y permanecerá mientras el alcance esté activo (por ejemplo, si es una actividad, hasta que finalice o el proceso finalice)

`Precaución: Un ViewModel nunca debe hacer referencia a una vista, a un Lifecycle o a una clase que pueda hacer referencia al contexto de la actividad.
 La única responsabilidad de ViewModel es administrar los datos para la IU.`
 
Los objetos ViewModel pueden contener LifecycleObservers, como objetos LiveData. Sin embargo, los objetos ViewModel no deben observar cambios en los elementos optimizados para ciclos de vida, como los objetos LiveData.
 
Si ViewModel necesita el contexto de Application, por ejemplo, para buscar un servicio del sistema, puede extender la clase [**AndroidViewModel**](https://developer.android.com/reference/androidx/lifecycle/AndroidViewModel) y lograr que un constructor reciba la Application, ya que la clase Application extiende Context.

## ViewModelProvider

[**ViewModelProvider**](https://developer.android.com/reference/androidx/lifecycle/ViewModelProvider) es una clase de utilidad que proporciona ViewModels para un alcance. El ViewModelProvider predeterminado para una actividad o un fragmento se puede obtener pasándolo a ViewModelProvider (ViewModelStoreOwner):

```kotlin
// "this" is the owner, activity or fragment
val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
```

### ViewModelProvider.Factory

Las implementaciones de la interfaz Factory son responsables de instanciar ViewModels.

## ViewModelStore

[**ViewModelStore**](https://developer.android.com/reference/androidx/lifecycle/ViewModelStore) es una clase para almacenar ViewModels.

Si un propietario de ViewModel se destruye y se vuelve a crear debido a cambio de configuración, la nueva instancia de un propietario aún debería tener la misma instancia anterior de ViewModelStore.

Pero si un propietario se destruye y no se va a volver a crear, entonces se llama a `ViewModelStore#clear()`, por lo que se notificará a ViewModels que ya no se utilizan.

Utiliza `ViewModelStoreOwner.getViewModelStore()` para recuperar un ViewModelStore para actividades y fragmentos.




## References

androidx.lifecycle.ViewModel
androidx.lifecycle.ViewModelProvider
androidx.lifecycle.ViewModelStore

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.