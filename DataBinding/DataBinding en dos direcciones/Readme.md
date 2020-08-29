# Android con Kotlin - Data Binding en dos direcciones

*Código de ejemplo de Data Binding en dos direcciones en Android con Kotlin.*

La biblioteca de vinculación de datos admite la vinculación de datos bidireccional. Admite la capacidad de recibir cambios de datos en una propiedad y, al mismo tiempo, escuchar las actualizaciones de los usuarios a esa propiedad.
  
La vinculación de datos bidireccional, recibe cambios en los datos de la propiedad y escucha las actualizaciones de los usuarios al mismo tiempo.

Vinculación de datos unidireccional:

```xml
<CheckBox
    android:id="@+id/rememberMeCheckBox"
    android:checked="@{viewmodel.rememberMe}"
    android:onCheckedChanged="@{viewmodel.rememberMeChanged}" />
```

Vinculación de datos bidireccional.
La notación **@={}**, que incluye el signo **"="**:
```xml
<CheckBox
    android:id="@+id/rememberMeCheckBox"
    android:checked="@={viewmodel.rememberMe}" />
```





## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.


```xml
```
```kotlin
```