# Android con Kotlin - ViewModel

*Este ejemplo muestra las siguientes características de [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)*:

* Implementación de un ViewModel
* 

# Documentación

La clase [**ViewModel**](https://developer.android.com/reference/androidx/lifecycle/ViewModel) está diseñada para almacenar y administrar los datos para una Activity o un Fragment de manera optimizada para los ciclos de vida, lo cual permite que los datos sobrevivan a cambios de configuración. También maneja la comunicación de la Activity / Fragment con el resto de la aplicación (por ejemplo, llamando a las clases de lógica de negocios).

## Implementación de un ViewModel

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

Obtenga una instancia de viewModel existente o cree uno nuevo usando [**ViewModelProvider**](https://developer.android.com/reference/androidx/lifecycle/ViewModelProvider), una clase de utilidad que proporciona ViewModels para un alcance (generalmente, un fragmento o una actividad):
`````kotlin
val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
// or using activity-ktx artifact
val viewModel: MainViewModel by viewModels()
`````

Y permanecerá mientras el alcance esté activo (por ejemplo, si es una actividad, hasta que finalice o el proceso finalice)

`Precaución: Un ViewModel nunca debe hacer referencia a una vista, a un Lifecycle o a una clase que pueda hacer referencia al contexto de la actividad.
 La única responsabilidad de ViewModel es administrar los datos para la IU.`
 
Los objetos ViewModel pueden contener LifecycleObservers, como objetos LiveData. Sin embargo, los objetos ViewModel no deben observar cambios en los elementos optimizados para ciclos de vida, como los objetos LiveData.

## ViewModel

La clase [**ViewModel**](https://developer.android.com/reference/androidx/lifecycle/ViewModel) se encarga de preparar y administrar los datos para una Activityo una Fragment. También maneja la comunicación de la Actividad / Fragmento con el resto de la aplicación (por ejemplo, llamando a las clases de lógica de negocios).

Un ViewModel siempre se crea en asociación con un alcance (un fragmento o una actividad) y se conservará mientras el alcance esté activo.

Esto significa que un ViewModel no se destruirá si su propietario se destruye para un cambio de configuración (por ejemplo, rotación). La nueva instancia del propietario simplemente se volverá a conectar al ViewModel existente.

El propósito de ViewModel es adquirir y conservar la información necesaria para una actividad o un fragmento. La Actividad o el Fragmento deberían poder observar cambios en el ViewModel. Los ViewModels suelen exponer esta información a través de un LiveDataenlace de datos de Android. También puede utilizar cualquier construcción de observabilidad de su marco favorito.

La única responsabilidad de ViewModel es administrar los datos de la interfaz de usuario. Nunca debe acceder a su jerarquía de vista ni retener una referencia a la Actividad o al Fragmento.

ViewModels también se puede utilizar como capa de [comunicación entre diferentes Fragmentos de una Actividad](https://github.com/arbems/Android-with-Kotlin-Architecture-Components/tree/master/ViewModel/Compartir%20datos%20entre%20fragmentos%20usando%20ViewModel). 

## AndroidViewModel

La clase [**AndroidViewModel**](https://developer.android.com/reference/androidx/lifecycle/AndroidViewModel) es una subclase de ViewModel y, al igual que ellos, están diseñados para almacenar y administrar datos relacionados con la interfaz de usuario y son responsables de preparar y proporcionar datos para la interfaz de usuario y permitir que los datos sobrevivan automáticamente al cambio de configuración.

La única diferencia es que AndroidViewModel viene con el contexto de la aplicación, lo que es útil si necesita contexto para obtener un servicio del sistema o si tiene un requisito similar.

Si ViewModel necesita el contexto de Application, por ejemplo, para buscar un servicio del sistema, puede extender la clase **AndroidViewModel** y lograr que un constructor reciba la Application, ya que la clase Application extiende Context.

## ViewModelProvider

La clase [**ViewModelProvider**](https://developer.android.com/reference/androidx/lifecycle/ViewModelProvider) es una clase de utilidad que proporciona ViewModels para un alcance. El ViewModelProvider predeterminado para una actividad o un fragmento se puede obtener pasándolo a ViewModelProvider ([**ViewModelStoreOwner**](https://developer.android.com/reference/androidx/lifecycle/ViewModelStoreOwner))):

    ViewModelProvider(ViewModelStoreOwner owner)
####
    ViewModelProvider(ViewModelStoreOwner owner, ViewModelProvider.Factory factory)
####   
    ViewModelProvider(ViewModelStore store, ViewModelProvider.Factory factory)


### ViewModelStoreOwner

Un alcance que posee ViewModelStore.

Una responsabilidad de la implementación de esta interfaz es retener ViewModelStore durante los cambios de configuración y llamar a *ViewModelStore.clear()*, cuando este alcance se va a destruir.

Utiliza `ViewModelStoreOwner.getViewModelStore()` para recuperar un ViewModelStore para actividades y fragmentos.


### ViewModelProvider.Factory

Las implementaciones de la interfaz [**Factory**](https://developer.android.com/reference/androidx/lifecycle/ViewModelProvider.Factory) son responsables de instanciar ViewModels.

### ViewModelStore

[**ViewModelStore**](https://developer.android.com/reference/androidx/lifecycle/ViewModelStore) es una clase para almacenar ViewModels.

Si un propietario de ViewModel se destruye y se vuelve a crear debido a cambio de configuración, la nueva instancia de un propietario aún debería tener la misma instancia anterior de ViewModelStore.

Pero si un propietario se destruye y no se va a volver a crear, entonces se llama a `ViewModelStore#clear()`, por lo que se notificará a ViewModels que ya no se utilizan.




## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.