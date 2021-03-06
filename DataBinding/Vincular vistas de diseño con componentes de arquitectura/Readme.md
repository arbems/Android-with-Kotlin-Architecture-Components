# Android con Kotlin - Data Binding - Vincular vistas de diseño con componentes de arquitectura

*Este ejemplo muestra las siguientes características de [Data Binding Library](https://developer.android.com/topic/libraries/data-binding/index.html)*:

* Variables y expresiones de diseño
* Observabilidad a través de LiveData y objetos Observables (clase BaseObservable)
* Integración perfecta con ViewModel

# Documentación

**Data Binding Library** funciona a la perfección con los componentes de la arquitectura para simplificar aún más el desarrollo de la IU. 
Se pueden vincular los diseños de tu app a los datos de los componentes de la arquitectura, que ya te ayudan a administrar el ciclo de vida de los controladores de la IU y a notificar cambios en los datos.

## LiveData

Puedes usar objetos **LiveData** para notificar automáticamente a la IU cambios en los datos.

A diferencia de los objetos que implementan **Observable**, los objetos LiveData conocen el ciclo de vida de los observadores suscritos a los cambios de datos. 
[Ver ventajas de usar LiveData](https://github.com/arbems/Android-with-Kotlin-Architecture-Components/tree/master/LiveData)

Para usar un objeto LiveData con tu clase de vinculación, debes especificar un propietario del ciclo de vida a fin de definir el alcance del objeto LiveData:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    // Inflate view and obtain an instance of the binding class.
    val binding: UserBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    // Specify the current activity as the lifecycle owner.
    binding.LifecycleOwner = this
}
```

En el componente ViewModel, puedes usar el objeto LiveData para transformar los datos o combinar varias fuentes de datos.

```kotlin
private val _name = MutableLiveData<String>()
val name: LiveData<String> = Transformations.map(_name) { name ->
    name.capitalize(Locale.ROOT)
}
```

## ViewModel 

Usa ViewModel para administrar datos relacionados con la IU. Data Binding Library funciona a la perfección con los componentes **ViewModel**, que exponen los datos que el diseño observa y a cuyos cambios reacciona. 

Así podrás mover la lógica de IU fuera de los diseños y hacia los componentes, que son más fáciles de probar. 
Para usar el componente **ViewModel** con la biblioteca de Data Binding:

* Debes crear una instancia del componente ViewModel, que hereda de la clase ViewModel:
```kotlin
private val viewModel: MyViewModel by viewModels()
```
* Obtener una instancia de la clase de vinculación:
```kotlin
val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
```
* Y asignar el componente ViewModel a la clase de vinculación:
```kotlin
binding.viewmodel = viewModel
```

En el diseño, asigna las propiedades y métodos del componente ViewModel a las vistas correspondientes utilizando expresiones de vinculación:
```xml
<CheckBox
        android:id="@+id/rememberMeCheckBox"
        android:checked="@{viewModel.rememberMe}"
        android:onCheckedChanged="@{() -> viewModel.rememberMeChanged()}" />
    
```


## ViewModel Observable 

Para tener más control sobre los adaptadores de vinculación

Puedes usar un componente ViewModel que implemente el [**Observable**](https://developer.android.com/reference/android/databinding/Observable) para notificar a otros componentes de la app sobre cambios en los datos, de manera similar a cómo usarías un objeto LiveData.

Hay situaciones en las que podrías preferir usar un componente ViewModel que implemente la **interfaz Observable** en lugar de usar objetos **LiveData**, incluso si pierdes las funcionalidades de administración del ciclo de vida de LiveData.

Si usas un componente ViewModel que implemente **Observable**, tendrás más control sobre los **adaptadores de vinculación** en tu app. Este patrón brinda más control sobre las notificaciones cuando cambian los datos. También permite especificar un método personalizado para establecer el valor de un atributo en la vinculación de datos bidireccional.

#### Implementar ViewModel Observable

Un ViewModel que también es un Observable, para ser utilizado con la Librería de Data Binding.

Crear una clase que se herede de la clase ViewModel e implemente la interfaz Observable:

```kotlin
// A ViewModel that is also an Observable, to be used with the Data Binding Library.
open class ObservableViewModel : ViewModel(), Observable {
    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()

    @get:Bindable
    var name: String = ""
        set(value) {
            field = value

            notifyPropertyChanged(BR.name)
        }

    override fun addOnPropertyChangedCallback(
            callback: Observable.OnPropertyChangedCallback) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(
            callback: Observable.OnPropertyChangedCallback) {
        callbacks.remove(callback)
    }

    // Notifies observers that all properties of this instance have changed.
    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    // Notifies observers that a specific property has changed. The getter for the property that changes should be marked with the @Bindable annotation to generate a field in the BR class to be used as the fieldId parameter.
    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }
}
```

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.