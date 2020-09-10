# Android con Kotlin - Coroutines

# Documentación

* [Context]()
* [Scope]()
* [Builders]()
* [Funciones de suspensión]()

![scheme coroutines kotlin](https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Architecture-Components/master/Corrutinas%20kotlin%20con%20componentes%20de%20la%20arquitectura/Coroutines/0001.png)

# Context

Cada corrutina en Kotlin tiene un **contexto** que está representado por una instancia de la [interfaz CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/). Un contexto es un *conjunto de elementos* y el contexto actual de la rutina está disponible a través de la propiedad `coroutineContext`.

```kotlin
println("My context is: $coroutineContext")
```

Todos los constructores de corrutinas, como *launch* y *async*, aceptan un parámetro opcional de **CoroutineContext** que se puede utilizar para especificar explícitamente el [CoroutineDispatcher](https://kotlin.github.io/kotlinx.corrutinas/kotlinx-corrutinas-core/kotlinx.corrutinas/-corrutina-dispatcher/index.html) para la nueva corrutina y otros elementos de contexto como el [Job](https://kotlin.github.io/kotlinx.corrutinas/kotlinx-corrutinas-core/kotlinx.corrutinas/-job/index.html) de la corrutina, handleException o el nombre de la corrutina.

Podemos usar el operador **+** para definir el *conjunto de elementos* para un contexto:

```kotlin
launch(Dispatchers.Default + job + handleException + CoroutineName("test")) { }
```

## [interface CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/)

**CoroutineContext** es un *indexed set* de instancias de [Element](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.corrutinas/-corrutina-context/-element/). Un conjunto indexado es una mezcla entre un set y un map. Cada *Element* de este conjunto tiene una [Key](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.corrutinas/-corrutina-context/-key.html).

<img src="https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Architecture-Components/master/Corrutinas%20kotlin%20con%20componentes%20de%20la%20arquitectura/Coroutines/0002.png" width="600" /><br>

**Keys** que nos sirven para obtener los cuatro *Element* de nuestro **CoroutineContext**:

* **Job**: Obtenemos el *Job* de la corrutina a la que se asocia el contexto.
* **ContinuationInterceptor**: Obtenemos el *CoroutineDispatcher* de la corrutina a la que se asocia el contexto.
* **CoroutineExceptionHandler**: Obtenemos el *manejador de excepciones* de la corrutina a la que se asocia el contexto.
* **CoroutineName**: Obtenemos el *nombre de la corrutina* a la que se asocia el contexto. Establecer un nombre es útil para efectos de depuración.

El contexto de la corrutina es inmutable, pero puede agregar elementos a un contexto usando el operador `plus`.
Podemos combinar elementos de un contexto con los elementos de otro contexto gracias al operador `plus`, devolviendo un nuevo contexto que contiene los elementos combinados.

### [Job](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html)

**Job** es parte del contexto. Una corrutina en sí misma está representada por un *Job*. Es responsable del ciclo de vida, la cancelación y las relaciones entre padres e hijos de la corrutina.

Los **Jobs** se pueden organizar en jerarquías de padres e hijos donde la cancelación de un padre conduce a la cancelación inmediata de todos sus hijos de forma recursiva.<br>

Cuando se lanza una corrutina en el *CoroutineScope* de otra corrutina, hereda su contexto a través de *CoroutineScope.coroutineContext* y el trabajo de la nueva corrutina se convierte en un elemento secundario del trabajo de la corrutina principal. Cuando se cancela la corrutina principal, todos sus elementos secundarios también se cancelan de forma recursiva.

Sin embargo, cuando se utiliza [GlobalScope](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-global-scope/index.html) para iniciar una corrutina, no hay un padre para el trabajo de la nueva corrutina. Por lo tanto, no está vinculado al alcance desde el que se lanzó y funciona de forma independiente.

Una corrutina padre siempre espera la finalización de todos sus hijos. Un padre no tiene que rastrear explícitamente a todos los hijos que lanza, y no tiene que usar Job.join para esperarlos al final.

### [Dispatchers](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/index.html)

El **Dispatcher** de corrutina determina qué hilo o hilos utiliza la correspondiente corrutina para su ejecución.
Puede limitar la ejecución de corrutinas a un hilo específico, enviarlo a un grupo de hilos o dejar que se ejecute *unconfined*.

<img src="https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Architecture-Components/master/Corrutinas%20kotlin%20con%20componentes%20de%20la%20arquitectura/Coroutines/0003.png" witdh="600"/>

Object Dispatcher agrupa varias implementaciones de *CoroutineDispatcher*:

* **Dispatchers.Default**: *CoroutineDispatcher* por defecto que utilizan todos los constructores estándar como launch, async, etc. si no se especifica un dispatcher ni ningún otro *ContinuationInterceptor* en su contexto. Utiliza un grupo común de subprocesos en segundo plano compartidos. Ésta es una opción adecuada para corrutinas informáticas intensivas que consumen recursos de la CPU, como cálculos, algoritmos, etc.

* **Dispatchers.IO**: *CoroutineDispatcher* que está diseñado para descargar tareas de E/S de bloqueo a un grupo compartido de subprocesos. En general, todas las tareas que bloquearán el hilo mientras esperan la respuesta de otro sistema: peticiones al servidor, acceso a la base de datos, sitema de archivos, sensores etc.

* **Dispatchers.Main**: *CoroutineDispatcher* que se limita al subproceso principal que opera con objetos de IU. Por lo general, estos *Dispatchers* son de un solo subproceso.

* **Dispatchers.Unconfined**: *CoroutineDispatcher* que inicia una corrutina en el hilo del llamador, pero solo hasta el primer punto de suspensión. Después de la suspensión, reanuda la corrutina en el hilo que está totalmente determinada por la función de suspensión que se invocó. Es apropiado para corrutinas que no consumen tiempo de CPU ni actualizan ningún dato compartido (como la interfaz de usuario) confinado a un hilo específico. *Dispatchers.Unconfined* no debe usarse en código general.

Elegir el **Dispatcher** incorrecto puede reducir o anular la efectividad de la corrutina, a tener en cuenta para elegir *Dispatcher*:

* Si el código interactúa con los elementos de la interfaz de usuario, *Dispatchers.Main* es apropiado.
* Si el código es intensivo en CPU. Es decir, el código realiza cálculos (CPU), *Dispatchers.Default* es apropiado ya que está respaldado por un grupo de subprocesos con tantos subprocesos como núcleos de CPU.
* El código es intensivo en IO. Es decir, el código se comunica a través de la red / archivo (IO). *Dispatchers.IO* es apropiado.

### Coroutine Name

Los identificadores asignados automáticamente son buenos cuando las corrutinas se registran con frecuencia y solo necesita correlacionar los registros que provienen de la misma corrutina. Sin embargo, cuando una corrutina está vinculada al procesamiento de una solicitud específica o al realizar alguna tarea específica en segundo plano, es mejor nombrarla explícitamente para fines de depuración. El elemento de contexto CoroutineName tiene el mismo propósito que el nombre del hilo. Se incluye en el nombre del hilo que está ejecutando esta corrutina cuando el modo de depuración está activado.

### Unconfined vs confined dispatcher

???

# Scope

### interface CoroutineScope

Hay una interfaz llamada [CoroutineScope](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html) consta de una única propiedad `coroutineContext`:

```kotlin
public interface CoroutineScope {
    public abstract val coroutineContext: kotlin.coroutines.CoroutineContext
}
```

# Builders

# Funciones de suspensión

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
