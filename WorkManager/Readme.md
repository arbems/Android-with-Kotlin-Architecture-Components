# Android con Kotlin - Work manager (Android Jetpack)

*Proyecto con códigos de ejemplos de [Work manager](https://developer.android.com/topic/libraries/architecture/workmanager) en Android con Kotlin.*

Programa y ejecuta tareas en segundo plano diferibles y basadas en restricciones.

La API de WorkManager facilita la programación de tareas asíncronas diferibles que se deben ejecutar de manera confiable. Estas API te permiten crear una tarea y entregarla a WorkManager para que la ejecute cuando se cumplan las restricciones de trabajo.


## Coroutines vs. WorkManager

* **El alcance de ambos es diferente**, la API de WorkManager facilita la especificación de tareas asíncronas diferibles y cuándo deben ejecutarse. 
Estas API le permiten crear una tarea y entregarla a WorkManager para que se ejecute inmediatamente o en el momento adecuado.<br>
Por otro lado, las corrutinas están diseñadas para calcular una tarea determinada solo de forma inmediata y asíncrona.

* **Garantía de que el sistema ejecutará las tareas**, WorkManager está diseñado para tareas que requieren una garantía de que el sistema las ejecutará incluso si la aplicación se cierra, como cargar datos de la aplicación en un servidor.


Si su objetivo es escribir código limpio sin devoluciones de llamada explícitamente construidas que pase a tareas en segundo plano, encontrará que las **corrutinas** son la única opción.
El uso de corrutinas de ninguna manera excluye el uso de **WorkManager** o cualquier otra herramienta para las operaciones en segundo plano que elija.


## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
