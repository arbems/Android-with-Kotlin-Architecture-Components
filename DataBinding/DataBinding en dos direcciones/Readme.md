# Android con Kotlin - Data Binding - Vinculación de datos bidireccional

*Código de ejemplo de vinculación de datos bidireccional usando Data Binding en Android con Kotlin.*

La librería Data Binding admite la vinculación de datos bidireccional. Admite la capacidad de recibir cambios de datos en una propiedad y, al mismo tiempo, escuchar las actualizaciones de los usuarios a esa propiedad.
  
La notación **@={}**, que incluye el signo **"="**:
```xml
<CheckBox
    android:id="@+id/rememberMeCheckBox"
    android:checked="@={viewmodel.rememberMe}" />
```

Para reaccionar a los cambios en los datos, puedes hacer que tu variable de diseño sea una implementación de *Observable*, generalmente **BaseObservable**, y usar un *@Bindable*:

```kotlin
class User() : BaseObservable() {
    // La anotación Bindable se debe aplicar a cualquier método de acceso getter de una clase Observable.
    // Bindable generará un campo en la clase BR para identificar el campo que ha cambiado.
    @get:Bindable
    var name: String = ""
        set(value) {
            field = value

            // Notifica a los oyentes que una propiedad específica ha cambiado.
            notifyPropertyChanged(BR.name)
        }
}
```


## Vinculación de datos bidireccional con atributos personalizados

Android proporciona implementaciones de vinculación de datos bidireccional para los [atributos bidireccionales más comunes](https://developer.android.com/topic/libraries/data-binding/two-way#two-way-attributes) y objetos de escucha de cambio, que puedes usar como parte de la app.
<br />Por ejemplo, la clase *TextView* atributo *android:text* y adaptador de vinculación	*TextViewBindingAdapter*.

Si deseas utilizar la vinculación de datos bidireccional con atributos personalizados, debes trabajar con las anotaciones [**@InverseBindingAdapter**](https://developer.android.com/reference/android/databinding/InverseBindingAdapter) y [**@InverseBindingMethod**](https://developer.android.com/reference/android/databinding/InverseBindingMethod).

1. Anota el método que establece el valor inicial y se actualiza cuando el valor cambia con @BindingAdapter:

```kotlin
@BindingAdapter("android:text")
fun setText(view: EditText, value: Int) {
    if(view.text.toString() != value.toString() && value != 0)
        view.setText(value.toString())
}
```

2. Anota el método que lee el valor de la vista con @InverseBindingAdapter:

```kotlin
@InverseBindingAdapter(attribute = "android:text")
fun getText(view: EditText): Int {
    return if(view.text.toString() == "") 0 else view.text.toString().toInt()
}

```

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.