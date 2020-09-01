# Android con Kotlin - Data Binding y objetos de datos observables

*Código de ejemplo de Data Binding y objetos de datos observables en Android con Kotlin.*

La observabilidad es la capacidad de un objeto para notificar a otros sobre cambios en sus datos. La biblioteca de vinculación de datos te permite hacer que objetos, campos o colecciones sean observables.

Se puede usar cualquier objeto para la vinculación de datos, pero modificar el objeto no hace que la IU se actualice automáticamente. La vinculación de datos se puede utilizar para otorgar a los objetos de datos la capacidad de notificar a otros objetos, conocidos como objetos de escucha, cuando cambian sus datos. Hay tres tipos diferentes de clases observables: objetos, campos y colecciones.

Cuando uno de esos objetos de datos observables está vinculado a la IU y una propiedad del objeto de datos cambia, la IU se actualiza automáticamente.

## Campos observables

Crear clases que implementen la interfaz Observable requiere un poco de trabajo. Hacerlo no valdría la pena si las clases solo tuvieran unas pocas propiedades. 
En este caso, se pueden usar clases de campo observables en lugar de crear un objeto observable, usa la **clase genérica Observable** y **clases primitivas específicas** para que los campos sean observables:

* [**ObservableBoolean**](https://developer.android.com/reference/android/databinding/ObservableBoolean?hl=es-419)
* [**ObservableByte**](https://developer.android.com/reference/android/databinding/ObservableByte?hl=es-419)
* [**ObservableChar**](https://developer.android.com/reference/android/databinding/ObservableChar?hl=es-419)
* [**ObservableShort**](https://developer.android.com/reference/android/databinding/ObservableShort?hl=es-419)
* [**ObservableInt**](https://developer.android.com/reference/android/databinding/ObservableInt?hl=es-419)
* [**ObservableLong**](https://developer.android.com/reference/android/databinding/ObservableLong?hl=es-419)
* [**ObservableFloat**](https://developer.android.com/reference/android/databinding/ObservableFloat?hl=es-419)
* [**ObservableDouble**](https://developer.android.com/reference/android/databinding/ObservableDouble?hl=es-419)
* [**ObservableParcelable**](https://developer.android.com/reference/android/databinding/ObservableParcelable?hl=es-419)
* [**ObservableField**](https://developer.android.com/reference/android/databinding/ObservableField?hl=es-419)

Ejemplo campos observables:

```kotlin
class User(
    val name: ObservableField<String>,
    val firstName: ObservableField<String>,
    val lastName: ObservableField<String>,
    val age: ObservableInt
) {
    // Fields calculates
    val yearOfBirth: ObservableInt = object : ObservableInt(age) {
        override fun get(): Int {
            LocalDate.now().apply {
                return year - age.get()
            }
        }
    }

    val fullName: ObservableField<String> =
        object : ObservableField<String>(name, firstName, lastName) {
            override fun get(): String? {
                return "${name.get()?.capitalize()} ${firstName.get()?.capitalize()} ${lastName.get()?.capitalize()}"
            }
        }
}
```

Para acceder al valor del campo, usa los métodos de acceso **set()** y **get()**, o bien la sintaxis de la propiedad:

```kotlin
fun setUser(name: String, firstName: String, lastName: String, age: String) {
    user.name.set(name)
    user.firstName.set(firstName)
    user.lastName.set(lastName)

    if(age != "")
        user.age.set(age.toInt())
}
```

`Nota: Android Studio 3.1 y versiones posteriores te permiten reemplazar campos observables con objetos LiveData, que ofrecen beneficios adicionales para tu app.`

### Colecciones observables

Algunas apps usan estructuras dinámicas para almacenar datos. Las colecciones observables permiten acceder a esas estructuras mediante una clave.

* [**ObservableArrayMap**](https://developer.android.com/reference/android/databinding/ObservableArrayMap?hl=es-419)
* [**ObservableArrayList**](https://developer.android.com/reference/android/databinding/ObservableArrayList?hl=es-419)

La clase ObservableArrayMap es útil cuando la clave es un tipo de referencia (por ejemplo, String).

```kotlin
val languages: ObservableArrayMap<String, Any>

ObservableArrayMap<String, Any>().apply {
    set("es", "Spanish")
}
```

La clase ObservableArrayList es útil cuando la clave es un número entero.

```kotlin
val languages: ObservableArrayList<Any>

ObservableArrayList<Any>().apply {
    add("Spanish")
}
```


## Objetos observables

### Interfaz Observable

Una clase que implementa la interfaz [**Observable**](https://developer.android.com/reference/android/databinding/Observable) permite registrar objetos de escucha que desean recibir notificaciones de cambios de propiedad en el objeto observable.

La interfaz **Observable** tiene un mecanismo para agregar y quitar objetos de escucha, pero debes decidir cuándo se envían las notificaciones. Para ello, asigna una anotación **@Bindable** al método *get* y llama al método **notifyPropertyChanged()** en el método *set*.

```kotlin
class User : Observable {

    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()

    @get:Bindable
    var name: String = ""
        set(value) {
            field = value

            // Notifica a los oyentes que una propiedad específica ha cambiado.
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var age: Int = 0
        set(value) {
            field = value

            notifyPropertyChanged(BR.age)
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
    private fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    // Notifies observers that a specific property has changed.
    private fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }
}
```

### Clase BaseObservable

Para facilitar el desarrollo, **Data Binding** proporciona la clase [**BaseObservable**](https://developer.android.com/reference/android/databinding/BaseObservable), que implementa el mecanismo de registro del objeto de escucha. 

La clase de datos que implementa **BaseObservable** es responsable de notificar cuándo cambian las propiedades. Para ello, asigna una anotación **@Bindable** al método *get* y llama al método **notifyPropertyChanged()** en el método *set*, como se muestra en el siguiente ejemplo:

```kotlin
class User() : BaseObservable() {

    @get:Bindable
    var name: String = ""
        set(value) {
            field = value

            // Notifica a los oyentes que una propiedad específica ha cambiado.
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var age: Int = 0
        set(value) {
            field = value

            notifyPropertyChanged(BR.age)
        }
}
```

Data Binding genera una clase denominada BR en el paquete del módulo que contiene los ID de los recursos utilizados para la vinculación de datos. La anotación Bindable genera una entrada en el archivo de la clase BR durante la compilación. 

`Nota: Si no se puede cambiar la clase base para las clases de datos, se puede implementar la interfaz Observable utilizando un objeto PropertyChangeRegistry a fin de registrar y notificar a los objetos de escucha de manera eficiente.`


## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.