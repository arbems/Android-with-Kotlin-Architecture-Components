# Android con Kotlin - ViewModels - Básico

Código de ejemplo de una aplicación simple con ViewModel en Android con Kotlin.
                                                                                                  
La clase ViewModel está diseñada para almacenar y administrar datos relacionados con la IU de manera optimizada para los ciclos de vida, permite que los datos sobrevivan a cambios de configuración.

Se encarga de preparar y administrar los datos para una Activity o una Fragment. También maneja la comunicación de la Activity / Fragment con el resto de la aplicación (por ejemplo, llamando a las clases de lógica de negocios).

Precaución: Un ViewModel nunca debe hacer referencia a una vista, a un Lifecycle o a una clase que pueda hacer referencia al contexto de la actividad.
La única responsabilidad de ViewModel es administrar los datos para la IU.

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.