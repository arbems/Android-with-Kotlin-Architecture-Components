# Android con Kotlin - Corrutinas kotlin con componentes de la arquitectura

*Proyecto con códigos de ejemplo de [Corrutinas]() en Android con Kotlin.*

#### [Coroutines]()

#### [Coroutines con LiveData]()

# Documentación:

Las **corrutinas** de Kotlin proporcionan una API que te permite escribir código asíncrono. 
Puedes definir un **CoroutineScope**, lo que te ayuda a administrar cuándo deben ejecutarse las corrutinas. 
Cada operación asíncrona se ejecuta dentro de un alcance particular.

En Android, las corrutinas ayudan a administrar tareas de larga duración que, de lo contrario, podrían bloquear el subproceso principal y hacer que una app dejara de responder.

Las corrutinas son la solución recomendada para la **programación asíncrona en Android**. Por las siguientes razones:

* **Ligereza**: Puedes ejecutar muchas corrutinas en un solo subproceso debido a la compatibilidad con la **suspensión**, que no bloquea el subproceso en el que se ejecuta la corrutina. Ahora, la suspensión ahorra más memoria que el bloqueo y admite muchas operaciones simultáneas.

* **Menos fugas de memoria**: Usa la *simultaneidad estructurada* para ejecutar operaciones dentro de un alcance.

* **Compatibilidad con cancelación incorporada**: Se propaga automáticamente la cancelación a través de la jerarquía de corrutinas en ejecución.

* **Integración con Jetpack**: Muchas bibliotecas de Jetpack incluyen extensiones que proporcionan compatibilidad total con corrutinas. Además, algunas bibliotecas proporcionan su propio alcance de corrutina, que puedes usar para la simultaneidad estructurada.

Desde el punto de vista del rendimiento, las coroutines permiten la ejecución de miles y hasta millones de hilos concurrentemente con un uso de recursos eficiente haciendo más robusta la aplicación al ser más difícil de alcanzar un error que indique falta de memoria.



## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.