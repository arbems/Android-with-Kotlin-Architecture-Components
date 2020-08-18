# Android con Kotlin - DataBinding y ViewModel

La biblioteca de DataBinding funciona a la perfección con los componentes **ViewModel**, que exponen los datos que el diseño observa y a cuyos cambios reacciona. 

Si usas los componentes **ViewModel** con la biblioteca de DataBinding, podrás mover la lógica de IU fuera de los diseños y hacia los componentes, que son más fáciles de probar. 

Para usar el componente **ViewModel** con la biblioteca de DataBinding:
* Debes crear una instancia de tu componente, que hereda de la clase ViewModel.
* Obtener una instancia de la clase de vinculación.
* Y asignar el componente ViewModel a una propiedad en la clase de vinculación.

```kotlin
class ViewModelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Obtain the ViewModel component.
        val userModel: UserModel by viewModels()

        // Inflate view and obtain an instance of the binding class.
        val binding: UserBinding = DataBindingUtil.setContentView(this, R.layout.user)

        // Assign the component to a property in the binding class.
        binding.viewmodel = userModel
    }
}
```

En el diseño, asigna las propiedades y métodos del componente ViewModel a las vistas correspondientes utilizando expresiones de vinculación:



## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.