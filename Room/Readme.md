# Android con Kotlin - Room (Biblioteca de Jetpack)
                                                        
*Proyecto con códigos de ejemplo de [librería Room](https://developer.android.com/topic/libraries/architecture/room) en Android con Kotlin.*

[**Room con LiveData**](https://github.com/arbems/Android-with-Kotlin-Architecture-Components/tree/master/Room/Room%20con%20LiveData)

# Documentación

## SqlLite vs. Room

* En el caso de SQLite, no hay comprobaciones en tiempo de compilación de consultas SQL.
    * Room hace comprbaciones de consultas SQL en tiempo de compilación.

* Actualizar la base de datos, cambiar el esquema fue difícil en SqlLite pero no en Room con clases de migración.

* Con Sqlite, la conversión de consultas SQL a objetos java es difícil y requiere demasiado código repetitivo.
    * Con Room, asigna objetos de base de datos a objetos java de manera muy fácil con poco código repetitivo.

* SqlLite no puede trabajar con LiveData y ViewModel, mientras que Room está construido para trabajar con LiveData y ViewModel.

## Anotaciones de Room y componentes principales:

### Database

Una clase abstracta con anotación **@Database** y extiende de clase **RoomDatabase**.

### Entity

Anotación **@Entity** en una clase representa una tabla en nuestra base de datos.

### DAO

Para cada entidad una interfaz DAO es necesaria para que contenga funciones de acceso a base de datos.





## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.