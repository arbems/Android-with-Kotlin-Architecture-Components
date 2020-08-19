# Android con Kotlin - LiveData con Room

*Código de ejemplo de LiveData con Room en Android con Kotlin.*

La biblioteca de persistencias Room admite consultas observables, que muestran objetos LiveData.

Room genera todo el código necesario para actualizar el objeto LiveData cuando se actualiza una base de datos.

El código generado ejecuta la consulta de manera asíncrona en un subproceso en segundo plano cuando es necesario. Este patrón es útil para mostrar los datos en una IU sincronizada con los datos almacenados en una base de datos.

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.