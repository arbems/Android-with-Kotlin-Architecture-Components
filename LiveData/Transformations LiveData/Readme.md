# Android con Kotlin - Transformar LiveData

*Código de ejemplo de una aplicación de como transformar LiveData en Android con Kotlin.*

Es posible que desees realizar cambios en el valor almacenado en un objeto **LiveData** antes de enviarlo a los observadores, o tal vez debas devolver una instancia LiveData diferente según el valor de otro. 
El paquete **Lifecycle** proporciona la clase [**Transformations**](https://developer.android.com/reference/androidx/lifecycle/Transformations), que incluye métodos de ayuda que admiten estas situaciones.

## Transformations

Métodos de transformación para LiveData. Las transformaciones se calculan de forma diferida y solo se ejecutarán cuando se observe el LiveData devuelto.

### map

`map(LiveData<X> source, Function<X, Y> mapFunction)`

Aplica una función *mapFunction* al valor almacenado en el objeto LiveData, y propaga el resultado de manera descendente.

```kotlin
val userLiveData: LiveData<User> = UserLiveData()
val userName: LiveData<String> = Transformations.map(userLiveData) {
    user -> "${user.name} ${user.lastName}"
}    
```

Estas transformaciones no se calculan a menos que un observador esté observando el objeto LiveData devuelto.


### switchMap

Similar al mapa, aplica una función al valor almacenado en el objeto LiveData y desenvuelve y distribuye el resultado en sentido descendente. 
La función pasada a **switchMap()** debe devolver un objeto LiveData.

```kotlin
private fun getUser(id: String): LiveData<User> {
  // ...
}
val userId: LiveData<String> = ...
val user = Transformations.switchMap(userId) { id -> getUser(id) }
```

### distinctUntilChanged


## MediatorLiveData

Para implementar tu propia transformación, puedes usar la clase MediatorLiveData, que implementa objetos de escucha para otros objetos LiveData y procesa los eventos emitidos por estos. 
MediatorLiveData propaga correctamente su estado al objeto LiveData de origen.


## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.