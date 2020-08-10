# Android con Kotlin - ViewModel y LiveData

Código de ejemplo de una aplicación con ViewModel y LiveData en Android con Kotlin.

LiveData es una clase contenedora de datos y tienen la capacidad de ser observable y está optimizada para ciclos de vida, lo que significa que respeta el ciclo de vida de otros componentes de las apps, como actividades, fragmentos o servicios.

Asegúrate de almacenar objetos LiveData que actualicen la IU en objetos ViewModel, en lugar de una actividad o un fragmento.

Para evitar las actividades y fragmentos demasiado cargados y para desacoplar las instancias LiveData de instancias de fragmentos o actividades específicas, y permitir que los objetos LiveData sobrevivan a los cambios de configuración.

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
