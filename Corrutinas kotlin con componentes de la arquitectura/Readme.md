# Android con Kotlin - Corrutinas kotlin con componentes de la arquitectura

Una **corrutina** es un patrón de diseño de simultaneidad que puedes usar en Android para simplificar el código que se ejecuta de forma asíncrona.

En Android, las corrutinas ayudan a administrar tareas de larga duración que, de lo contrario, podrían bloquear el subproceso principal y hacer que tu app dejara de responder.

Las corrutinas son la solución recomendada para la programación asíncrona en Android. Por las siguientes razones:

* **Ligereza**: Puedes ejecutar muchas corrutinas en un solo subproceso debido a la compatibilidad con la *suspensión*, que no bloquea el subproceso en el que se ejecuta la corrutina. Ahora, la suspensión ahorra más memoria que el bloqueo y admite muchas operaciones simultáneas.

* **Menos fugas de memoria**: Usa la simultaneidad estructurada para ejecutar operaciones dentro de un alcance.

* **Compatibilidad con cancelación incorporada**: Se propaga automáticamente la cancelación a través de la jerarquía de corrutinas en ejecución.

* **Integración con Jetpack**: Muchas bibliotecas de Jetpack incluyen extensiones que proporcionan compatibilidad total con corrutinas. Además, algunas bibliotecas proporcionan su propio alcance de corrutina, que puedes usar para la simultaneidad estructurada.

Desde el punto de vista del rendimiento, las coroutines permiten la ejecución de miles y hasta millones de hilos concurrentemente con un uso de recursos eficiente haciendo más robusta la aplicación al ser más difícil de alcanzar un error que indique falta de memoria.



## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.