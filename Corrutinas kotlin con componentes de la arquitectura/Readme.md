# Android con Kotlin - Corrutinas kotlin con componentes de la arquitectura

*Proyecto con códigos de ejemplo de [Corrutinas]() en Android con Kotlin.*

#### [Corrutinas en objeto ViewModel]()

#### [Corrutinas en objeto Lifecycle]()

#### [Suspender corrutinas optimizadas para ciclos de vida]()

#### [Corrutinas con LiveData](https://github.com/arbems/Android-with-Kotlin-Architecture-Components/tree/master/Corrutinas%20kotlin%20con%20componentes%20de%20la%20arquitectura/Usar%20corrutinas%20con%20LiveData)

# Documentación:

Las **corrutinas** de Kotlin proporcionan una API que te permite escribir código asíncrono. Puedes definir un **CoroutineScope**, lo que te ayuda a administrar cuándo deben ejecutarse las corrutinas. Cada operación asíncrona se ejecuta dentro de un alcance particular.

En Android, las corrutinas ayudan a administrar tareas de larga duración que, de lo contrario, podrían bloquear el hilo principal y hacer que una app dejara de responder.

Las corrutinas son la solución recomendada para la **programación asíncrona en Android**. Por las siguientes razones:

* **Ligereza**: Puedes ejecutar muchas corrutinas en un solo subproceso debido a la compatibilidad con la **suspensión**, que no bloquea el subproceso en el que se ejecuta la corrutina. Ahora, la suspensión ahorra más memoria que el bloqueo y admite muchas operaciones simultáneas.

* **Menos fugas de memoria**: Usa la *simultaneidad estructurada* para ejecutar operaciones dentro de un alcance.

* **Compatibilidad con cancelación incorporada**: Se propaga automáticamente la cancelación a través de la jerarquía de corrutinas en ejecución.

* **Integración con Jetpack**: Muchas bibliotecas de Jetpack incluyen extensiones que proporcionan compatibilidad total con corrutinas. Además, algunas bibliotecas proporcionan su propio alcance de corrutina, que puedes usar para la simultaneidad estructurada.

Desde el punto de vista del rendimiento, las coroutines permiten la ejecución de miles y hasta millones de hilos concurrentemente con un uso de recursos eficiente haciendo más robusta la aplicación al ser más difícil de alcanzar un error que indique falta de memoria.

# Ámbitos de corrutinas optimizados para ciclos de vida

## ViewModelScope

La biblioteca de *ViewModel KTX* ofrece una función **viewModelScope()** que facilita el lanzamiento de corrutinas desde tu *ViewModel*. El *CoroutineScope* está vinculado a *Dispatchers.Main* y se cancela automáticamente cuando se borra el *ViewModel*. Puedes usar *viewModelScope()* en lugar de crear un nuevo alcance para cada ViewModel.

Extensión de KTX:
`androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0`

Se define un **ViewModelScope** para cada objeto ViewModel de tu app. Si se borra ViewModel, se cancela automáticamente cualquier corrutina iniciada en este alcance.
Es útil para cuando tienes trabajos que se deben hacer solo si *ViewModel* está activo

Ejemplo que  lanza una corrutina que realiza una solicitud de red en un subproceso en segundo plano. 
La biblioteca maneja toda la configuración y la liberación del alcance correspondiente:

```kotlin
class MainViewModel : ViewModel() {
    // Realizar una solicitud de red sin bloquear el hilo de la interfaz de usuario
    private fun makeNetworkRequest() {
        // lanzar una corrutina en viewModelScope
        viewModelScope.launch  {
            remoteApi.slowFetch()
            // ...
        }
    }
    // No es necesario sobrescribir onCleared()
}
```

## LifecycleScope

*Lifecycle KTX* define un **LifecycleScope** para cada objeto Lifecycle. Se cancelan todas las corrutinas iniciadas en este alcance cuando se destruye el *Lifecycle*. Puedes acceder al *CoroutineScope* del Lifecycle mediante las propiedades *lifecycle.coroutineScope* o *lifecycleOwner.lifecycleScope*.

Extensión de KTX:
`androidx.lifecycle:lifecycle-runtime-ktx:2.2.0`

Ejemplo para usar lifecycleOwner.lifecycleScope para crear texto procesado previamente de forma asíncrona:

```kotlin
class MyFragment: Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            val params = TextViewCompat.getTextMetricsParams(textView)
            val precomputedText = withContext(Dispatchers.Default) {
                PrecomputedTextCompat.create(longTextContent, params)
            }
            TextViewCompat.setPrecomputedText(textView, precomputedText)
        }
    }
}
```

# Suspender corrutinas optimizadas para ciclos de vida

# Corrutinas con LiveData

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.