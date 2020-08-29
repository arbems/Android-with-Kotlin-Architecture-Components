# Android con Kotlin - Data Binding - Vincular vistas de diseño con componentes de arquitectura

*Código de ejemplo de como vincular vistas de diseño con componentes de arquitectura usando Data Binding, ViewModel y LiveData en Android con Kotlin.*

La biblioteca de AndroidX incluye los componentes de la arquitectura, los cuales se pueden usar para diseñar apps sólidas, que puedan someterse a pruebas y que admitan mantenimiento. La Biblioteca de vinculación de datos funciona a la perfección con los componentes de la arquitectura para simplificar aún más el desarrollo de la IU. Se pueden vincular los diseños de tu app a los datos de los componentes de la arquitectura, que ya te ayudan a administrar el ciclo de vida de los controladores de la IU y a notificar cambios en los datos.

## LiveData para notificar a la IU los cambios en los datos

Puedes usar objetos LiveData como fuente de vinculación de datos para notificar automáticamente a la IU cambios en los datos.

A diferencia de los objetos que implementan Observable, como los campos observables, los objetos LiveData conocen el ciclo de vida de los observadores suscritos a los cambios de datos.

Para usar un objeto LiveData con tu clase de vinculación, debes especificar un propietario del ciclo de vida a fin de definir el alcance del objeto LiveData:

```kotlin
class ViewModelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Inflate view and obtain an instance of the binding class.
        val binding: UserBinding = DataBindingUtil.setContentView(this, R.layout.user)

        // Specify the current activity as the lifecycle owner.
        binding.LifecycleOwner = this
    }
}
```

En el componente ViewModel, puedes usar el objeto LiveData para transformar los datos o combinar varias fuentes de datos.

```kotlin
class ScheduleViewModel : ViewModel() {
    val userName: LiveData

    init {
        val result = Repository.userName
        userName = Transformations.map(result) { result -> result.value }
    }
}
```

## ViewModel para administrar datos relacionados con la IU

La biblioteca de Data Binding funciona a la perfección con los componentes **ViewModel**, que exponen los datos que el diseño observa y a cuyos cambios reacciona. 

Si usas los componentes **ViewModel** con la biblioteca de Data Binding, podrás mover la lógica de IU fuera de los diseños y hacia los componentes, que son más fáciles de probar. 

Para usar el componente **ViewModel** con la biblioteca de Data Binding:
* Debes crear una instancia de tu componente, que hereda de la clase ViewModel.
* Obtener una instancia de la clase de vinculación.
* Y asignar el componente ViewModel a una propiedad en la clase de vinculación.

```kotlin
class ViewModelActivity : AppCompatActivity() {
    // Obtain the ViewModel component.
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Inflate view and obtain an instance of the binding class.
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.user)

        // Assign the component to a property in the binding class.
        binding.viewmodel = viewModel
    }
}
```

En el diseño, asigna las propiedades y métodos del componente ViewModel a las vistas correspondientes utilizando expresiones de vinculación:

```xml
<CheckBox
        android:id="@+id/rememberMeCheckBox"
        android:checked="@{viewModel.rememberMe}"
        android:onCheckedChanged="@{() -> viewModel.rememberMeChanged()}" />
    
```


### ViewModel observable para tener más control sobre los adaptadores de vinculación



## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.