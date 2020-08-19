# Android con Kotlin - Objetos LiveData

*Código de ejemplo de como trabajar con objetos LiveData en Android con Kotlin.*

### Crear objetos LiveData

* Los objetos [**LiveData**](https://developer.android.com/reference/androidx/lifecycle/LiveData) van normalmente en el ViewModel, puede usar con cualquier dato, incluidos los objetos que implementan Collections.
```kotlin
val _rememberMe = MutableLiveData<Boolean>()
val rememberMe: LiveData<Boolean> get() = _rememberMe
```
**Hay que almacenar los objetos LiveData que actualicen la IU en objetos ViewModel, en lugar de una activity o fragment:**
* Para evitar las actividades y fragmentos demasiado cargados 
* Para desacoplar las instancias LiveData de instancias de fragmentos o actividades específicas, y permitir que los objetos LiveData sobrevivan a los cambios de configuración.

### Observar objetos LiveData

El método *onCreate()* de un componente de la aplicación es el lugar adecuado para comenzar a observar un objeto LiveData, para evitar llamadas redundantes y para garantizar que la actividad o el fragmento tenga datos que pueda mostrar tan pronto como quede activo.

En general, LiveData solo brinda actualizaciones cuando los datos cambian, y solo a observadores activos. Una excepción a este comportamiento es que los observadores también reciben una actualización cuando cambian de un estado activo a un estado inactivo. Además, si el observador cambia de inactivo a activo por segunda vez, solo recibe una actualización si el valor cambió desde la última vez que estuvo activo.

Comenzar a observar un objeto *LiveData* creando objeto [**Observer**](https://developer.android.com/reference/androidx/lifecycle/Observer):
```kotlin
val observer = Observer<Boolean> { newRememberMe ->
    // Update the UI
    textView.text = newRememberMe.toString()
}
```
Conecta el Observer con liveData llamando al método [**observe()**](https://developer.android.com/reference/androidx/lifecycle/LiveData#observe(androidx.lifecycle.LifecycleOwner,%20androidx.lifecycle.Observer%3C?%20super%20T%3E)), y pasar parámetros activity como LifecycleOwner y el Observer:
```kotlin
viewModel.rememberMe.observe(this, observer)
```
Después de llamar a método observe() y pasar observer como parámetro, onChanged() se invoca de inmediato y proporciona el valor más reciente almacenado en mRememberMe. Si el objeto LiveData no estableció un valor en mRememberMe, no se llama a onChanged().

Cuando actualizas el valor almacenado en el objeto LiveData, activa todos los observadores registrados, siempre que el LifecycleOwner conectado esté en el estado activo.

LiveData permite que los observadores del controlador de IU se suscriban a actualizaciones. Cuando los datos retenidos por el objeto LiveData cambian, la IU se actualiza automáticamente en respuesta.

`Nota: Puedes registrar un observador sin un objeto LifecycleOwner, con el método observeForever(Observer), se considera que el observador siempre está activo y, por lo tanto, siempre recibe notificaciones sobre los cambios. Puedes quitar esos observadores llamando al método removeObserver(Observer).`

### Actualizar objetos LiveData

LiveData no tiene métodos disponibles públicamente para actualizar los datos almacenados. La clase [**MutableLiveData**](https://developer.android.com/reference/androidx/lifecycle/MutableLiveData) expone los métodos **setValue(T)** y **postValue(T)** de forma pública.

Por lo general, **MutableLiveData** se usa en ViewModel y, luego, ViewModel solo expone objetos LiveData inmutables a los observadores.
```kotlin
private val _rememberMe = MutableLiveData<Boolean>()
val rememberMe: LiveData<Boolean> get() = _rememberMe
```
Actualizar el valor del objeto LiveData, llamando a [**setValue(T)**](https://developer.android.com/reference/androidx/lifecycle/MutableLiveData#setValue(T)) o [**postValue(T)**](https://developer.android.com/reference/androidx/lifecycle/MutableLiveData#postValue(T)), la llamada a setValue() o postValue() activa observadores y actualiza la IU:
```kotlin
_rememberMe.value = newRememberMe
```
`Nota: Debes llamar al método setValue(T) para actualizar el objeto LiveData del subproceso principal. Si el código se ejecuta en un subproceso del trabajador, puedes usar el método postValue(T) en su lugar para actualizar el objeto LiveData.`


## Enlaces

[Reference LiveData](https://developer.android.com/reference/androidx/lifecycle/LiveData)

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.