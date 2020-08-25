# Android con Kotlin - Transformar LiveData

*Código de ejemplo de una aplicación de como transformar LiveData en Android con Kotlin.*

Es posible que desees realizar cambios en el valor almacenado en un objeto LiveData antes de despacharlo a los observadores, o tal vez debas devolver una instancia LiveData diferente según el valor de otro. El paquete Lifecycle proporciona la clase [**Transformations**](https://developer.android.com/reference/androidx/lifecycle/Transformations), que incluye métodos de ayuda que admiten estas situaciones.

## Transformations

### map

Aplica una función al valor almacenado en el objeto LiveData, y propaga el resultado de manera descendente.



### switchMap

### distinctUntilChanged


## MediatorLiveData



## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.