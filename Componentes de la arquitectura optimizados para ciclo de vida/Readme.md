# Android con Kotlin - Componentes de la arquitectura optimizados para ciclo de vida

*Proyecto con códigos de ejemplo de [Lifecycle Library](https://developer.android.com/topic/libraries/architecture/lifecycle) en Android con Kotlin.*

* Gestionar los ciclos de vida con componentes de la arquitectura

# Documentación

**Lifecycle** compila componentes optimizados para ciclos de vida que puedan ajustar el comportamiento según el estado actual del ciclo de vida de una actividad o un fragmento.

Los componentes optimizados para ciclo de vida realizan acciones en respuesta a un cambio en el estado del ciclo de vida de otro componente, como actividades o fragmentos. 

Implementar las acciones de los componentes dependientes en los métodos del ciclo de vida de actividades y fragmentos genera una organización deficiente del código y la proliferación de errores.
Si usas componentes optimizados para ciclos de vida, puedes sacar el código de los componentes dependientes, que se encuentra en los métodos del ciclo de vida, y colocarlo en los propios componentes.

El paquete **androidx.lifecycle** ofrece interfaces y clases que te permiten compilar componentes optimizados para ciclos de vida.

## Lifecycle

El componente LifeCycle se ocupa de los eventos de Android LifeCycle de un componente como Activity o Fragment, tiene tres clases principales:

* LifeCycle
* LifeCycleOwner
* LifeCycleObserver


[**Lifecycle**](https://developer.android.com/reference/androidx/lifecycle/Lifecycle) es una clase que mantiene la información sobre el estado del ciclo de vida de un componente como una actividad o un fragmento. 

Un objeto LifeCycle es un objeto que puede recuperar información sobre el ciclo de vida actual de un LifeCycleOwner, normalmente el propietario sería una actividad o un fragmento.

Los objetos LifeCycle proporcionan información sobre el ciclo de vida del propietario en términos de eventos y estados:

* Los **eventos** se envían con respecto a los eventos del propietario del ciclo de vida, como onCreate(), onStart(), onStop(),…

* El **estado** actual del componente al que le hace un seguimiento el objeto Lifecycle.

<img src="https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Architecture-Components/master/Componentes%20de%20la%20arquitectura%20optimizados%20para%20ciclo%20de%20vida/0001.png" width="700"></img>

Una clase puede supervisar el estado del ciclo de vida del componente agregando anotaciones a sus métodos. Luego, puedes agregar un observador llamando al método addObserver() de la clase Lifecycle y pasa una instancia de tu observador:

    internal class MyLocationListener(
        private val context: Context,
        private val lifecycle: Lifecycle,
        private val tag: String,
        private val callback: (Location) -> Unit
    ) : LifecycleObserver {
    
        private var enabled = false
    
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate() {
            Log.i(tag, "Lifecycle.Event.ON_CREATE - ${lifecycle.currentState}")
        }
    
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart() {
            if (enabled) {
                // connect
            }
    
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                Log.i(tag, "Lifecycle.Event.ON_START - ${lifecycle.currentState}")
            }
        }
    
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume() {
            Log.i(tag, "Lifecycle.Event.ON_RESUME - ${lifecycle.currentState}")
        }
    
        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause() {
            Log.i(tag, "Lifecycle.Event.ON_PAUSE - ${lifecycle.currentState}")
        }
    
        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop() {
            // disconnect if connected
            Log.i(tag, "Lifecycle.Event.ON_STOP - ${lifecycle.currentState}")
        }
    
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.DESTROYED)) {
                Log.i(tag, "Lifecycle.Event.ON_DESTROY - ${lifecycle.currentState}")
            }
        }
    
        fun enable() {
            enabled = true
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                // connect if not connected
            }
        }
    }
####
Activity:

    myLocationListener = MyLocationListener(this, lifecycle, "MyLocationListener") { location -> ... }
    lifecycle.addObserver(myLocationListener)
    
`Si necesitamos usar el MyLocationListener de otra actividad o fragmento, solo es necesario inicializarlo.`

#### [LifecycleOwner](https://developer.android.com/reference/androidx/lifecycle/LifecycleOwner)

LifecycleOwner es una interfaz de método único que indica que la clase tiene un Lifecycle. Tiene un método, getLifecycle().

Cualquier clase que implemente la interfaz LifeCycleOwner indica que tiene Android LifeCycle.

`Si, en cambio, intentas administrar el ciclo de vida de todo el proceso de una aplicación, consulta [ProcessLifecycleOwner](https://developer.android.com/reference/androidx/lifecycle/ProcessLifecycleOwner).`

Si una clase implementa **LifecycleObserver** y luego lo inicializa con el **Lifecycle** de la actividad en el método onCreate(). Esta acción permite que la clase sea autosuficiente, lo que significa que la lógica para reaccionar ante los cambios en el estado del ciclo de vida se declara en la clase.

El hecho de que los componentes individuales almacenen su propia lógica permite que la lógica de las actividades y los fragmentos sea más fácil de administrar.

La clase Lifecycle permite que otros objetos consulten el estado actual:

    if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
        // ...
    }
`Dado que varios estados se pueden intercalar para un momento determinado, si queremos verificar un estado específico, siempre usamos el método isAtLeast`

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.