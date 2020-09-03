# Android con Kotlin - ViewModels - Android KTX

Android KTX es un conjunto de extensiones de Kotlin que se incluyen con Android Jetpack y otras bibliotecas de Android. Las extensiones KTX proporcionan Kotlin conciso e idiomático a Jetpack, la plataforma de Android y otras API.

## ViewModels

### Activity KTX

Devuelve un delegado [Lazy] para acceder al ViewModel de ComponentActivity, si se especifica [factoryProducer], entonces [ViewModelProvider.Factory] devuelto por él se utilizará para crear [ViewModel] la primera vez:
```kotlin
val viewModel: SharedViewModel by viewModels()
```

### Fragment KTX

Puedes vincular a un ViewModel en una línea utilizando los delegados de propiedad viewModels y activityViewModels:

`dependencies {
     implementation "androidx.fragment:fragment-ktx:1.2.5"
 }`
 
**Fragment.activityViewModels(noinline factoryProducer: (() -> Factory)? = null)**

Devuelve un delegado de propiedad para acceder al [ViewModel] de la actividad principal, si se especifica [factoryProducer], entonces [ViewModelProvider.Factory] devuelto por él se utilizará para crear [ViewModel] la primera vez. De lo contrario, se utilizará [androidx.activity.ComponentActivity.getDefaultViewModelProviderFactory] de la actividad:
```kotlin
val viewModel: MyViewModel by activityViewModels()
// or
val viewModel by activityViewModels<MyViewModel>()
```

**Fragment.viewModels(
         noinline ownerProducer: () -> ViewModelStoreOwner = { this },
         noinline factoryProducer: (() -> Factory)? = null
     )**

Devuelve un delegado de propiedad para acceder a [ViewModel] de forma **predeterminada** en el ámbito de este [Fragmento]:
```kotlin
val viewModel: MyViewModel by viewModels()
// or 
val viewModel by viewModels<MyViewModel>()
```

### ViewModel KTX

La biblioteca de ViewModel KTX ofrece una función viewModelScope() que facilita el lanzamiento de corrutinas desde tu ViewModel.

`dependencies {
         implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"
     }`

A modo de ejemplo, la siguiente función viewModelScope() lanza una corrutina que realiza una solicitud de red en un subproceso en segundo plano. La biblioteca maneja toda la configuración y la liberación del alcance correspondiente:

```kotlin
class MainViewModel : ViewModel() {
    // Make a network request without blocking the UI thread
    private fun makeNetworkRequest() {
        // launch a coroutine in viewModelScope
        viewModelScope.launch  {
            remoteApi.slowFetch()
            /*...*/
        }
    }

    // No need to override onCleared()
}
```

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
