# Android con Kotlin - Data Binding y objetos de datos observables

*Código de ejemplo de Data Binding y objetos de datos observables en Android con Kotlin.*

La observabilidad es la capacidad de un objeto para notificar a otros sobre cambios en sus datos. La biblioteca de vinculación de datos te permite hacer que objetos, campos o colecciones sean observables.

Se puede usar cualquier objeto para la vinculación de datos, pero modificar el objeto no hace que la IU se actualice automáticamente. La vinculación de datos se puede utilizar para otorgar a los objetos de datos la capacidad de notificar a otros objetos, conocidos como objetos de escucha, cuando cambian sus datos. Hay tres tipos diferentes de clases observables: objetos, campos y colecciones.

Cuando uno de esos objetos de datos observables está vinculado a la IU y una propiedad del objeto de datos cambia, la IU se actualiza automáticamente.
## Campos y colecciones observables

Crear clases que implementen la interfaz Observable requiere un poco de trabajo. Hacerlo no valdría la pena si las clases solo tuvieran unas pocas propiedades. En este caso, usa la clase genérica Observable y clases primitivas específicas para que los campos sean observables.
```kotlin
import androidx.databinding.*

class User (
    val firstName: ObservableField<String>,
    val age: ObservableInt,
    val languages: ObservableArrayMap<String, Any>, // La clase ObservableArrayMap es útil cuando la clave es un tipo de referencia (por ejemplo, String)
    val actors: ObservableArrayList<Any> // La clase ObservableArrayList es útil cuando la clave es un número entero
)
```

Para acceder al valor del campo, usa los métodos de acceso set() y get(), o bien la sintaxis de la propiedad

```kotlin
user.firstName.set("Carlos")
user.age.set(28)
user.languages["es"] = "Spanish"
user.actors[0] = "Brad Pitt"
```

* ObservableBoolean
* ObservableByte
* ObservableChar
* ObservableShort
* ObservableInt
* ObservableLong
* ObservableFloat
* ObservableDouble
* ObservableParcelable

`Nota: Android Studio 3.1 y versiones posteriores te permiten reemplazar campos observables con objetos LiveData, que ofrecen beneficios adicionales para tu app.`

## Objetos observables

Una clase que implementa la interfaz [**Observable**](https://developer.android.com/reference/android/databinding/Observable) permite registrar objetos de escucha que desean recibir notificaciones de cambios de propiedad en el objeto observable.

La interfaz Observable tiene un mecanismo para agregar y quitar objetos de escucha, pero debes decidir cuándo se envían las notificaciones. Para facilitar el desarrollo, Data Binding proporciona la clase [**BaseObservable**](https://developer.android.com/reference/android/databinding/BaseObservable), que implementa el mecanismo de registro del objeto de escucha. La clase de datos que implementa BaseObservable es responsable de notificar cuándo cambian las propiedades. Para ello, asigna una anotación Bindable al método get y llama al método notifyPropertyChanged() en el método set, como se muestra en el siguiente ejemplo:

```kotlin
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class UserObservable : BaseObservable() {

    @get:Bindable
    var firstName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.firstName)
        }

    @get:Bindable
    var lastName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.lastName)
        }
}
```

Data Binding genera una clase denominada BR en el paquete del módulo que contiene los ID de los recursos utilizados para la vinculación de datos. La anotación Bindable genera una entrada en el archivo de la clase BR durante la compilación. Si no se puede cambiar la clase base para las clases de datos, se puede implementar la interfaz Observable utilizando un objeto PropertyChangeRegistry a fin de registrar y notificar a los objetos de escucha de manera eficiente.


## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.